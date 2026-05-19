package com.example.prestamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prestamos.dto.CreditoDTO;
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
}
