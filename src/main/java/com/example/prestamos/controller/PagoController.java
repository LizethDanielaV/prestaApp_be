package com.example.prestamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prestamos.dto.AbonoCreditoDTO;
import com.example.prestamos.model.Pago;
import com.example.prestamos.service.PagoService;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping("/credito/{id}")
    public ResponseEntity<Pago> abonarCredito(@PathVariable int id, @RequestBody AbonoCreditoDTO dto) {
        Pago pago = pagoService.abonarCredito(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pago);
    }
}
