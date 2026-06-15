package com.example.prestamos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prestamos.dto.CreditoDTO;
import com.example.prestamos.dto.CreditoDetalleDTO;
import com.example.prestamos.dto.CreditoResumenClienteDTO;
import com.example.prestamos.model.Cliente;
import com.example.prestamos.model.Credito;
import com.example.prestamos.model.Cuota;
import com.example.prestamos.model.Estado;
import com.example.prestamos.model.FrecuenciaPago;
import com.example.prestamos.repository.ClienteRepository;
import com.example.prestamos.repository.CreditoRepository;
import com.example.prestamos.repository.CuotaRepository;
import com.example.prestamos.repository.EstadoRepository;
import com.example.prestamos.repository.FrecuenciaPagoRepository;

@Service
public class CreditoService {

    @Autowired
    CreditoRepository creditoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    FrecuenciaPagoRepository frecuenciaPagoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CuotaRepository cuotaRepository;

    public List<Credito> listar() {
        return creditoRepository.findAll();
    }

    @Transactional
    public Credito crear(CreditoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteCedula())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + dto.getClienteCedula()));

        FrecuenciaPago frecuenciaPago = frecuenciaPagoRepository.findById(dto.getFrecuenciaPagoId().intValue())
                .orElseThrow(() -> new RuntimeException("Frecuencia de pago no encontrada: " + dto.getFrecuenciaPagoId()));

        Estado estadoCreditoAlDia = estadoRepository.findByNombreAndAplicaA("al_dia", "credito")
                .orElseThrow(() -> new RuntimeException("Estado 'al_dia' para credito no configurado"));

        Estado estadoCuotaPendiente = estadoRepository.findByNombreAndAplicaA("pendiente", "cuota")
                .orElseThrow(() -> new RuntimeException("Estado 'pendiente' para cuota no configurado"));

        // 1. Crear el crédito
        Credito credito = new Credito();
        credito.setMonto_prestamo(dto.getMontoPrestamo());
        credito.setSaldo_capital(dto.getMontoPrestamo()); // al inicio, saldo = monto prestado
        credito.setInteres(dto.getInteres());
        credito.setNumero_de_coutas(dto.getNumeroDeCuotas());
        credito.setFecha_prestamo(dto.getFechaPrestamo());
        credito.setFecha_limite(dto.getFechaLimite());
        credito.setFrecuenciaPago(frecuenciaPago);
        credito.setCliente(cliente);
        credito.setEstado(estadoCreditoAlDia);

        credito = creditoRepository.save(credito);

        // 2. Generar las cuotas según el plan
        List<Cuota> cuotas = generarCuotas(credito, frecuenciaPago, estadoCuotaPendiente);
        cuotaRepository.saveAll(cuotas);
        credito.setCuotas(cuotas);

        return credito;
    }

    private List<Cuota> generarCuotas(Credito credito, FrecuenciaPago frecuencia, Estado estadoCuotaPendiente) {
        List<Cuota> cuotas = new ArrayList<>();
        int numCuotas = credito.getNumero_de_coutas();

        float capitalBase = redondear(credito.getMonto_prestamo() / numCuotas);
        float capitalAcumulado = 0f;

        for (int i = 1; i <= numCuotas; i++) {
            Cuota cuota = new Cuota();
            cuota.setNumero_de_cuota(i);
            cuota.setFecha_esperada(credito.getFecha_prestamo().plusDays((long) frecuencia.getDias() * i));

            float capitalCuota;
            if (i == numCuotas) {
                // la última cuota absorbe la diferencia por redondeo
                capitalCuota = redondear(credito.getMonto_prestamo() - capitalAcumulado);
            } else {
                capitalCuota = capitalBase;
                capitalAcumulado += capitalCuota;
            }

            cuota.setMonto_capital(capitalCuota);
            cuota.setCapital_pagado(0f);
            cuota.setInteres_pagado(0f);
            cuota.setEstado(estadoCuotaPendiente);
            cuota.setCredito(credito);

            cuotas.add(cuota);
        }
        return cuotas;
    }

    private float redondear(float valor) {
        return Math.round(valor * 100) / 100f;
    }

    public void eliminar(int id) {
        if (!creditoRepository.existsById(id)) {
            throw new RuntimeException("Crédito no encontrado: " + id);
        }
        creditoRepository.deleteById(id);
    }

    public List<Credito> buscarPorCliente(Long cedula) {
        return creditoRepository.findByClienteCedula(cedula);
    }

@Transactional(readOnly = true)
public CreditoDetalleDTO obtenerDetalle(int id) {
    Credito c = creditoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Crédito no encontrado: " + id));

    List<Cuota> cuotas = c.getCuotas() != null ? c.getCuotas() : List.of();
    LocalDate hoy = LocalDate.now();

    int cuotasPagadas = (int) cuotas.stream()
            .filter(cuota -> cuota.getEstado() != null
                    && "pagada".equals(cuota.getEstado().getNombre()))
            .count();

    int cuotasVencidas = (int) cuotas.stream()
            .filter(cuota -> cuota.getEstado() == null
                    || !"pagada".equals(cuota.getEstado().getNombre()))
            .filter(cuota -> cuota.getFecha_esperada().isBefore(hoy))
            .count();

    float totalAbonado = 0f;
    for (Cuota cuota : cuotas) {
        totalAbonado += cuota.getCapital_pagado() + cuota.getInteres_pagado();
    }

    float deudaTotal = c.getSaldo_capital() * (1 + c.getInteres() / 100f);

    return new CreditoDetalleDTO(
            c.getId_credito(),
            c.getMonto_prestamo(),
            c.getFecha_prestamo(),
            c.getFecha_limite(),
            c.getInteres(),
            c.getNumero_de_coutas(),
            c.getFrecuenciaPago().getNombre(),
            cuotasPagadas,
            cuotasVencidas,
            totalAbonado,
            deudaTotal
    );
}

    @Transactional(readOnly = true)
    public List<CreditoResumenClienteDTO> resumenPorCliente(Long cedula) {
    List<Credito> creditos = creditoRepository.findByClienteCedula(cedula);
    List<CreditoResumenClienteDTO> resumen = new ArrayList<>();

    for (int i = 0; i < creditos.size(); i++) {
        Credito c = creditos.get(i);

        List<Cuota> cuotas = c.getCuotas() != null ? c.getCuotas() : List.of();

        int cuotasPagadas = (int) cuotas.stream()
                .filter(cuota -> cuota.getEstado() != null
                        && "pagada".equals(cuota.getEstado().getNombre()))
                .count();

        resumen.add(new CreditoResumenClienteDTO(
                c.getId_credito(),
                "Credito " + (i + 1),
                c.getMonto_prestamo(),
                c.getSaldo_capital(),
                cuotasPagadas,
                c.getNumero_de_coutas()
        ));
    }

    return resumen;
    }
}
