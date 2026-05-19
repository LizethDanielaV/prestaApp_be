package com.example.prestamos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.prestamos.model.Zona;
import com.example.prestamos.repository.ZonaRepository;

@Service
public class ZonaService {

    @Autowired
    ZonaRepository zonaRepository;

    public Zona save(Zona zona) {
        return zonaRepository.save(zona);
    }

    public List<Zona> findAll() {
        return zonaRepository.findAll();
    }
}
