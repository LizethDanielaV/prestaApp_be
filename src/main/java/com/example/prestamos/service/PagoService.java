package com.example.prestamos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prestamos.dto.AbonoCreditoDTO;
import com.example.prestamos.model.Abono;
import com.example.prestamos.model.Credito;
import com.example.prestamos.model.Cuota;
import com.example.prestamos.model.Estado;
import com.example.prestamos.model.MetodoDePago;
import com.example.prestamos.model.Pago;
import com.example.prestamos.repository.AbonoRepository;
import com.example.prestamos.repository.CreditoRepository;
import com.example.prestamos.repository.CuotaRepository;
import com.example.prestamos.repository.EstadoRepository;
import com.example.prestamos.repository.MetodoDePagoRepository;
import com.example.prestamos.repository.PagoRepository;

@Service
public class PagoService {

    @Autowired
    private CreditoRepository creditoRepository;

    @Autowired
    private CuotaRepository cuotaRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private AbonoRepository abonoRepository;

    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;

    @Transactional
    public Pago abonarCredito(int creditoId, AbonoCreditoDTO dto) {
        Credito credito = creditoRepository.findById(creditoId)
                .orElseThrow(() -> new RuntimeException("Crédito no encontrado: " + creditoId));

        MetodoDePago metodoDePago = metodoDePagoRepository.findById(dto.getMetodoPagoId())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado: " + dto.getMetodoPagoId()));

        if (dto.getBancoId() != null) {
            if (metodoDePago.getBanco() == null
                    || metodoDePago.getBanco().getId_banco() != dto.getBancoId()) {
                throw new RuntimeException("El banco enviado no coincide con el método de pago seleccionado");
            }
        }

        // 1. Crear la cabecera del pago
        Pago pago = new Pago();
        pago.setMonto_total(dto.getMonto());
        pago.setFecha_pago(dto.getFechaPago());
        pago.setTipo("UN_CREDITO");
        pago.setCliente(credito.getCliente());
        pago.setMetodoDePago(metodoDePago);
        pago = pagoRepository.save(pago);

        // 2. Distribuir el monto entre las cuotas pendientes
        List<Abono> abonos = distribuirPago(credito, dto.getMonto(), pago);
        abonoRepository.saveAll(abonos);

        // 3. Actualizar saldo y estado del crédito
        actualizarEstadoCredito(credito);
        creditoRepository.save(credito);

        pago.setAbonos(abonos);
        return pago;
    }

    private List<Abono> distribuirPago(Credito credito, float montoDisponible, Pago pago) {
        List<Abono> abonos = new ArrayList<>();

        Estado estadoPagada = estadoRepository.findByNombreAndAplicaA("pagada", "cuota")
                .orElseThrow(() -> new RuntimeException("Estado 'pagada' no configurado"));
        Estado estadoParcial = estadoRepository.findByNombreAndAplicaA("parcial", "cuota")
                .orElseThrow(() -> new RuntimeException("Estado 'parcial' no configurado"));

        List<Cuota> cuotasPendientes = credito.getCuotas().stream()
                .filter(c -> c.getEstado() == null || !"pagada".equals(c.getEstado().getNombre()))
                .sorted(Comparator.comparingInt(Cuota::getNumero_de_cuota))
                .toList();

        float restante = montoDisponible;

        for (Cuota cuota : cuotasPendientes) {
            if (restante <= 0) break;

            float capitalPendiente = cuota.getMonto_capital() - cuota.getCapital_pagado();

            float interesGenerado = credito.getSaldo_capital() * (credito.getInteres() / 100f);
            float interesPendiente = Math.max(0, interesGenerado - cuota.getInteres_pagado());

            float totalPendienteCuota = capitalPendiente + interesPendiente;

            float capitalAbonado;
            float interesAbonado;

            if (restante >= totalPendienteCuota) {
                capitalAbonado = capitalPendiente;
                interesAbonado = interesPendiente;
                cuota.setEstado(estadoPagada);
                cuota.setFecha_pago_real(pago.getFecha_pago());
            } else {
                interesAbonado = Math.min(restante, interesPendiente);
                capitalAbonado = restante - interesAbonado;
                cuota.setEstado(estadoParcial);
            }

            cuota.setCapital_pagado(cuota.getCapital_pagado() + capitalAbonado);
            cuota.setInteres_pagado(cuota.getInteres_pagado() + interesAbonado);
            credito.setSaldo_capital(credito.getSaldo_capital() - capitalAbonado);

            Abono abono = new Abono();
            abono.setPago(pago);
            abono.setCuota(cuota);
            abono.setCapital_abonado(capitalAbonado);
            abono.setInteres_abonado(interesAbonado);
            abono.setTotal_abonado(capitalAbonado + interesAbonado);
            abonos.add(abono);

            cuotaRepository.save(cuota);

            restante -= (capitalAbonado + interesAbonado);
        }

        return abonos;
    }

    private void actualizarEstadoCredito(Credito credito) {
        LocalDate hoy = LocalDate.now();

        boolean cancelado = credito.getSaldo_capital() <= 0;
        boolean atrasado = !cancelado && credito.getCuotas().stream()
                .anyMatch(c -> (c.getEstado() == null || !"pagada".equals(c.getEstado().getNombre()))
                        && c.getFecha_esperada().isBefore(hoy));

        String codigo = cancelado ? "cancelado" : (atrasado ? "atrasado" : "al_dia");

        Estado estado = estadoRepository.findByNombreAndAplicaA(codigo, "credito")
                .orElseThrow(() -> new RuntimeException("Estado '" + codigo + "' no configurado"));

        credito.setEstado(estado);
    }
}
