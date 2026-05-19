package com.example.prestamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prestamos.model.Estado;
import com.example.prestamos.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    EstadoService estadoService;

    @PostMapping
    public ResponseEntity<Estado> crear(@RequestBody Estado estado) {
        Estado estadoCreado = estadoService.save(estado);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(estadoCreado);
    }
}
