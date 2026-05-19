package com.example.prestamos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prestamos.model.Cuota;
import com.example.prestamos.repository.CuotaRepository;

@Service
public class CuotaService {

    @Autowired
    CuotaRepository cuotaRepository;

    public Cuota save(Cuota cuota) {
        return cuotaRepository.save(cuota);
    }
}
