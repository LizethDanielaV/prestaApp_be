package com.example.prestamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prestamos.model.Abono;
import com.example.prestamos.service.AbonoService;

@RestController
@RequestMapping("/abonos")
public class AbonoController {

    @Autowired
    AbonoService abonoService;

    @PostMapping
    public ResponseEntity<Abono> crear(@RequestBody Abono abono) {
        Abono abonoCreado = abonoService.save(abono);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(abonoCreado);
    }
}
