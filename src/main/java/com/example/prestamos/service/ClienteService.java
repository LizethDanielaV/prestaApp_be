package com.example.prestamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prestamos.dto.ClienteDTO;
import com.example.prestamos.dto.ClienteResumenZonaDTO;
import com.example.prestamos.model.Cliente;
import com.example.prestamos.model.Credito;
import com.example.prestamos.model.Cuota;
import com.example.prestamos.model.Referencia;
import com.example.prestamos.model.Zona;
import com.example.prestamos.repository.ClienteRepository;
import com.example.prestamos.repository.ReferenciaRepository;
import com.example.prestamos.repository.ZonaRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ReferenciaRepository referenciaRepository;

    @Autowired
    ZonaRepository zonaRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ClienteResumenZonaDTO> listarPorZona(Integer zonaId) {
        zonaRepository.findById(zonaId)
                .orElseThrow(() -> new RuntimeException("Zona no encontrada: " + zonaId));

        List<Cliente> clientes = clienteRepository.findByZonaId(zonaId);

        return clientes.stream().map(cliente -> {
            List<Credito> creditos = cliente.getCreditos() != null ? cliente.getCreditos() : List.of();

            int cantidadCreditos = creditos.size();

            double montoTotalPrestamo = creditos.stream()
                    .mapToDouble(Credito::getMonto_prestamo)
                    .sum();

            double saldoPendiente = creditos.stream()
                    .flatMap(c -> c.getCuotas() != null ? c.getCuotas().stream() : java.util.stream.Stream.empty())
                    .filter(cuota -> cuota.getFecha_pago_real() == null)
                    .mapToDouble(Cuota::getMonto_total)
                    .sum();

            return new ClienteResumenZonaDTO(
                    cliente.getCedula(),
                    cliente.getNombre(),
                    cliente.getDireccion(),
                    cantidadCreditos,
                    montoTotalPrestamo,
                    saldoPendiente
            );
        }).toList();
    }

    public void eliminar(Long cedula) {
        Cliente cliente = clienteRepository.findById(cedula)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + cedula));
        if (cliente.getCreditos() != null && !cliente.getCreditos().isEmpty()) {
            throw new RuntimeException("No se puede eliminar el cliente porque tiene créditos registrados");
        }
        clienteRepository.deleteById(cedula);
    }

    public Cliente crear(ClienteDTO dto) {
        Zona zona = zonaRepository.findById(dto.getZonaId())
                .orElseThrow(() -> new RuntimeException("Zona no encontrada: " + dto.getZonaId()));

        Referencia referencia = null;
        if (dto.getReferencia() != null) {
            Referencia nuevaReferencia = new Referencia();
            nuevaReferencia.setCedula(dto.getReferencia().getCedula());
            nuevaReferencia.setNombre(dto.getReferencia().getNombre());
            nuevaReferencia.setCelular(dto.getReferencia().getCelular());
            nuevaReferencia.setDireccion(dto.getReferencia().getDireccion());
            referencia = referenciaRepository.save(nuevaReferencia);
        }

        Cliente cliente = new Cliente();
        cliente.setCedula(dto.getCedula());
        cliente.setNombre(dto.getNombre());
        cliente.setDireccion(dto.getDireccion());
        cliente.setZona(zona);
        cliente.setReferencia(referencia);

        return clienteRepository.save(cliente);
    }
}
