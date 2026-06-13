package com.example.prestamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prestamos.dto.ClienteResumenZonaDTO;
import com.example.prestamos.dto.cliente.ClienteCreateDTO;
import com.example.prestamos.dto.cliente.ClienteEditarDTO;
import com.example.prestamos.dto.cliente.ClienteResponseDTO;
import com.example.prestamos.mapper.ClienteMapper;
import com.example.prestamos.mapper.ReferenciaMapper;
import com.example.prestamos.model.Cliente;
import com.example.prestamos.model.Credito;
import com.example.prestamos.model.Cuota;
import com.example.prestamos.model.Referencia;
import com.example.prestamos.model.Zona;
import com.example.prestamos.repository.ClienteRepository;
import com.example.prestamos.repository.CreditoRepository;
import com.example.prestamos.repository.ReferenciaRepository;
import com.example.prestamos.repository.ZonaRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CreditoRepository creditoRepository;

    @Autowired
    ReferenciaRepository referenciaRepository;

    @Autowired
    ZonaRepository zonaRepository;

    @Autowired
    ReferenciaMapper referenciaMapper;

    @Autowired 
    ClienteMapper clienteMapper;

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listar() {

        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(clienteMapper::toResponseDto)
                .toList();
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


    public ClienteResponseDTO crear(ClienteCreateDTO dto) {
        Zona zona = zonaRepository.findById(dto.getZonaId())
                .orElseThrow(() -> new RuntimeException("Zona no encontrada: " + dto.getZonaId()));

        Referencia referencia = null;
        if (dto.getReferencia() != null) {
            Referencia nuevaReferencia = referenciaMapper.toEntity(dto.getReferencia());
            referencia = referenciaRepository.save(nuevaReferencia);
        }
        Cliente cliente = clienteMapper.toEntity(dto);
        cliente.setZona(zona);
        cliente.setReferencia(referencia);
        Cliente creado = clienteRepository.save(cliente);

        //transformo respuesta a DTO
        ClienteResponseDTO creadoDto = clienteMapper.toResponseDto(creado);
        //creadoDto.setZonaId(creado.getZona().getId());
        return creadoDto;
        /* 
        Cliente cliente = new Cliente();
        cliente.setCedula(dto.getCedula());
        cliente.setNombre(dto.getNombre());
        cliente.setDireccion(dto.getDireccion());
        cliente.setZona(zona);
        cliente.setReferencia(referencia);*/

        //return clienteRepository.save(cliente);
    }

    @Transactional
    public ClienteResponseDTO editar(Long cedula, ClienteEditarDTO dto) {
        Cliente cliente = clienteRepository.findById(cedula)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + cedula));

        Zona zona = cliente.getZona();
        if (dto.getZonaId() != null) {
            zona = zonaRepository.findById(dto.getZonaId())
                    .orElseThrow(() -> new RuntimeException("Zona no encontrada: " + dto.getZonaId()));
        }

        if (dto.getCedula() != null && !dto.getCedula().equals(cedula)) {
            if (clienteRepository.existsById(dto.getCedula())) {
                throw new RuntimeException("Ya existe un cliente con cedula: " + dto.getCedula());
            }

            Cliente clienteActualizado = new Cliente();
            clienteActualizado.setCedula(dto.getCedula());
            clienteActualizado.setNombre(dto.getNombre() != null ? dto.getNombre() : cliente.getNombre());
            clienteActualizado.setDireccion(dto.getDireccion() != null ? dto.getDireccion() : cliente.getDireccion());
            clienteActualizado.setZona(zona);
            clienteActualizado.setReferencia(cliente.getReferencia());

            Cliente guardado = clienteRepository.save(clienteActualizado);

            List<Credito> creditos = creditoRepository.findByClienteCedula(cedula);
            creditos.forEach(credito -> credito.setCliente(guardado));
            creditoRepository.saveAll(creditos);

            clienteRepository.delete(cliente);
            return clienteMapper.toResponseDto(guardado);
        }

        clienteMapper.updateEntityFromDto(dto, cliente);
        cliente.setZona(zona);

        Cliente actualizado = clienteRepository.save(cliente);
        return clienteMapper.toResponseDto(actualizado);
    }
}
