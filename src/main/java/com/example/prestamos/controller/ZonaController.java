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

import com.example.prestamos.model.Zona;
import com.example.prestamos.service.ZonaService;

@RestController
@RequestMapping("/zonas")
public class ZonaController {

    @Autowired
    ZonaService zonaService;

    @PostMapping()
    public ResponseEntity<Zona> crear(@RequestBody Zona zona) {
        Zona zonaCreada = zonaService.save(zona);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(zonaCreada);
    }

    @GetMapping()
    public ResponseEntity<List<Zona>> listar() {
        return ResponseEntity.ok(zonaService.findAll());
    }

}
