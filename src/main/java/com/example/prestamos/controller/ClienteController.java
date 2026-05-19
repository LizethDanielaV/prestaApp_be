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

import com.example.prestamos.dto.ClienteDTO;
import com.example.prestamos.dto.ClienteResumenZonaDTO;
import com.example.prestamos.model.Cliente;
import com.example.prestamos.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/zona/{zonaId}")
    public ResponseEntity<?> listarPorZona(@PathVariable Integer zonaId) {
        try {
            List<ClienteResumenZonaDTO> resultado = clienteService.listarPorZona(zonaId);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody ClienteDTO clienteDTO) {
        Cliente clienteCreado = clienteService.crear(clienteDTO);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(clienteCreado);
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<String> eliminar(@PathVariable Long cedula) {
        try {
            clienteService.eliminar(cedula);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
