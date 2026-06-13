package com.example.prestamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.prestamos.dto.zona.ZonaCreateRequestDTO;
import com.example.prestamos.dto.zona.ZonaResponseDTO;
import com.example.prestamos.service.ZonaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/zonas")
public class ZonaController {

    @Autowired
    ZonaService zonaService;

    @PostMapping()
    public ResponseEntity<ZonaResponseDTO> crear(@Valid @RequestBody ZonaCreateRequestDTO zonaDto) {
        ZonaResponseDTO zonaCreada = zonaService.create(zonaDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(zonaCreada);
    }

    @GetMapping()
    public ResponseEntity<List<ZonaResponseDTO>> listar() {
        return ResponseEntity.ok(zonaService.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ZonaResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ZonaCreateRequestDTO zonaDto){
        return ResponseEntity.ok( zonaService.actualizar(id, zonaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        zonaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
