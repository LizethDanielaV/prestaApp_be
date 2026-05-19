package com.example.prestamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prestamos.model.Cuota;
import com.example.prestamos.service.CuotaService;

@RestController
@RequestMapping("/cuotas")
public class CuotaController {

    @Autowired
    CuotaService cuotaService;

    @PostMapping
    public ResponseEntity<Cuota> crear(@RequestBody Cuota cuota) {
        Cuota cuotaCreada = cuotaService.save(cuota);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cuotaCreada);
    }
}
