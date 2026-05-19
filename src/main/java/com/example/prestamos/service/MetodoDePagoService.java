package com.example.prestamos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prestamos.model.MetodoDePago;
import com.example.prestamos.repository.MetodoDePagoRepository;

@Service
public class MetodoDePagoService {

    @Autowired
    MetodoDePagoRepository metodoDePagoRepository;

    public MetodoDePago save(MetodoDePago metodoDePago) {
        return metodoDePagoRepository.save(metodoDePago);
    }
}
