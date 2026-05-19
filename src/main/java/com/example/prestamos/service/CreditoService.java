package com.example.prestamos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prestamos.dto.CreditoDTO;
import com.example.prestamos.dto.CreditoDetalleDTO;
import com.example.prestamos.dto.CreditoResumenClienteDTO;
import com.example.prestamos.model.Cliente;
import com.example.prestamos.model.Credito;
import com.example.prestamos.model.FrecuenciaPago;
import com.example.prestamos.repository.ClienteRepository;
import com.example.prestamos.repository.CreditoRepository;
import com.example.prestamos.repository.FrecuenciaPagoRepository;

@Service
public class CreditoService {

    @Autowired
    CreditoRepository creditoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    FrecuenciaPagoRepository frecuenciaPagoRepository;

    public List<Credito> listar() {
        return creditoRepository.findAll();
    }

    public Credito crear(CreditoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteCedula())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + dto.getClienteCedula()));

        FrecuenciaPago frecuenciaPago = frecuenciaPagoRepository.findById(dto.getFrecuenciaPagoId().intValue())
                .orElseThrow(() -> new RuntimeException("Frecuencia de pago no encontrada: " + dto.getFrecuenciaPagoId()));

        Credito credito = new Credito();
        credito.setMonto_prestamo(dto.getMontoPrestamo());
        credito.setInteres(dto.getInteres());
        credito.setNumero_de_coutas(dto.getNumeroDeCuotas());
        credito.setFecha_prestamo(dto.getFechaPrestamo());
        credito.setFecha_limite(dto.getFechaLimite());
        credito.setFrecuenciaPago(frecuenciaPago);
        credito.setCliente(cliente);

        return creditoRepository.save(credito);
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

    public CreditoDetalleDTO obtenerDetalle(int id) {
        Credito c = creditoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédito no encontrado: " + id));

        float valorInteresPorCuota = c.getMonto_prestamo() * c.getInteres();

        return new CreditoDetalleDTO(
            c.getMonto_prestamo(),
            c.getFecha_prestamo(),
            c.getFecha_limite(),
            c.getInteres(),
            c.getNumero_de_coutas(),
            c.getFrecuenciaPago().getNombre(),
            valorInteresPorCuota,
            2,
            0,
            0,
            c.getMonto_prestamo()
        );
    }

    public List<CreditoResumenClienteDTO> resumenPorCliente(Long cedula) {
        List<Credito> creditos = creditoRepository.findByClienteCedula(cedula);
        List<CreditoResumenClienteDTO> resumen = new ArrayList<>();
        for (int i = 0; i < creditos.size(); i++) {
            Credito c = creditos.get(i);
            resumen.add(new CreditoResumenClienteDTO(
                "Credito " + (i + 1),
                c.getMonto_prestamo(),
                0,
                2,
                c.getNumero_de_coutas()
            ));
        }
        return resumen;
    }
}
