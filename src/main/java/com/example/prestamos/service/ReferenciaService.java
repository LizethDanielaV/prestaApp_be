package com.example.prestamos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prestamos.model.Referencia;
import com.example.prestamos.repository.ReferenciaRepository;

@Service
public class ReferenciaService {

    @Autowired
    ReferenciaRepository referenciaRepository;

    public Referencia save(Referencia referencia) {
        return referenciaRepository.save(referencia);
    }
}
