package com.example.prestamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prestamos.model.FrecuenciaPago;
import com.example.prestamos.service.FrecuenciaPagoService;

@RestController
@RequestMapping("/frecuencias-pago")
public class FrecuenciaPagoController {

    @Autowired
    FrecuenciaPagoService frecuenciaPagoService;

    @PostMapping()
    public ResponseEntity<FrecuenciaPago> crear(@RequestBody FrecuenciaPago frecuenciaPago) {
        FrecuenciaPago creada = frecuenciaPagoService.save(frecuenciaPago);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(creada);
    }

    @GetMapping()
    public ResponseEntity<List<FrecuenciaPago>> listar() {
        return ResponseEntity.ok(frecuenciaPagoService.findAll());
    }
}
