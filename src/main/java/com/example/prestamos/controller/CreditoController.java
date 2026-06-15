package com.example.prestamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prestamos.dto.CreditoDTO;
import com.example.prestamos.dto.CreditoDetalleDTO;
import com.example.prestamos.dto.CreditoResumenClienteDTO;
import com.example.prestamos.model.Credito;
import com.example.prestamos.service.CreditoService;

@RestController
@RequestMapping("/creditos")
public class CreditoController {

    @Autowired
    CreditoService creditoService;

    @GetMapping
    public ResponseEntity<List<Credito>> listar() {
        return ResponseEntity.ok(creditoService.listar());
    }

    @PostMapping
    public ResponseEntity<Credito> crear(@RequestBody CreditoDTO creditoDTO) {
        Credito creditoCreado = creditoService.crear(creditoDTO);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(creditoCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        try {
            creditoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/cliente/{cedula}")
    public ResponseEntity<List<Credito>> buscarPorCliente(@PathVariable Long cedula) {
        return ResponseEntity.ok(creditoService.buscarPorCliente(cedula));
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<?> obtenerDetalle(@PathVariable int id) {
        try {
            return ResponseEntity.ok(creditoService.obtenerDetalle(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/cliente/{cedula}/resumen")
    public ResponseEntity<List<CreditoResumenClienteDTO>> resumenPorCliente(@PathVariable Long cedula) {
        return ResponseEntity.ok(creditoService.resumenPorCliente(cedula));
    }

}
