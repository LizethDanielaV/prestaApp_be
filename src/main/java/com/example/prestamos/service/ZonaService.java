package com.example.prestamos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.prestamos.dto.zona.ZonaCreateRequestDTO;
import com.example.prestamos.dto.zona.ZonaResponseDTO;
import com.example.prestamos.mapper.ZonaMapper;
import com.example.prestamos.model.Cliente;
import com.example.prestamos.model.Zona;
import com.example.prestamos.repository.ClienteRepository;
import com.example.prestamos.repository.ZonaRepository;

import jakarta.transaction.Transactional;

@Service
public class ZonaService {

    @Autowired
    ZonaRepository zonaRepository;

    @Autowired
    ZonaMapper zonaMapper;

    @Autowired
    ClienteRepository clienteRepository;

    public ZonaResponseDTO create(ZonaCreateRequestDTO zonaDto) {
        Zona guardada = zonaRepository.save(zonaMapper.toEntity(zonaDto));
        return zonaMapper.toResponseDTO(guardada);
    }

    public List<ZonaResponseDTO> findAll() {
        return zonaRepository.findAll()
        .stream()
        .map(zonaMapper::toResponseDTO)
        .collect(Collectors.toList());
    }

    public ZonaResponseDTO actualizar(int id, ZonaCreateRequestDTO z){
        Zona zonaAnt = zonaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Zona not found"));
            
        zonaMapper.updateEntityFromDto(z, zonaAnt); 
        zonaRepository.save(zonaAnt);
        return zonaMapper.toResponseDTO(zonaAnt);
    }

    @Transactional
    public void delete(int id){
      Zona z = zonaRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Zona no encontrada: " + id));

      List<Cliente> clientesAsociados = clienteRepository.findByZonaId(id);

      if (!clientesAsociados.isEmpty()) {
          throw new RuntimeException("Esta zona no se puede borrar porque tiene clientes asociados");
      }
      
      zonaRepository.delete(z);
  }
}
