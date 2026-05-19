package com.example.prestamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prestamos.model.Referencia;
import com.example.prestamos.service.ReferenciaService;

@RestController
@RequestMapping("/referencias")
public class ReferenciaController {

    @Autowired
    ReferenciaService referenciaService;

    @PostMapping
    public ResponseEntity<Referencia> crear(@RequestBody Referencia referencia) {
        Referencia referenciaCreada = referenciaService.save(referencia);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(referenciaCreada);
    }
}
