package com.example.prestamos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prestamos.model.Abono;
import com.example.prestamos.repository.AbonoRepository;

@Service
public class AbonoService {

    @Autowired
    AbonoRepository abonoRepository;

    public Abono save(Abono abono) {
        return abonoRepository.save(abono);
    }
}
