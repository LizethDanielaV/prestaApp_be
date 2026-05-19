package com.example.prestamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prestamos.model.MetodoDePago;
import com.example.prestamos.service.MetodoDePagoService;

@RestController
@RequestMapping("/metodos-de-pago")
public class MetodoDePagoController {

    @Autowired
    MetodoDePagoService metodoDePagoService;

    @PostMapping
    public ResponseEntity<MetodoDePago> crear(@RequestBody MetodoDePago metodoDePago) {
        MetodoDePago metodoCreado = metodoDePagoService.save(metodoDePago);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(metodoCreado);
    }
}
