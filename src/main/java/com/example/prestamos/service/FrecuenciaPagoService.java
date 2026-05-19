package com.example.prestamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prestamos.model.FrecuenciaPago;
import com.example.prestamos.repository.FrecuenciaPagoRepository;

@Service
public class FrecuenciaPagoService {

    @Autowired
    FrecuenciaPagoRepository frecuenciaPagoRepository;

    public FrecuenciaPago save(FrecuenciaPago frecuenciaPago) {
        return frecuenciaPagoRepository.save(frecuenciaPago);
    }

    public List<FrecuenciaPago> findAll() {
        return frecuenciaPagoRepository.findAll();
    }
}
