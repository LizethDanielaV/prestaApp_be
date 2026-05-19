package com.example.prestamos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prestamos.model.Estado;
import com.example.prestamos.repository.EstadoRepository;

@Service
public class EstadoService {

    @Autowired
    EstadoRepository estadoRepository;

    public Estado save(Estado estado) {
        return estadoRepository.save(estado);
    }
}
