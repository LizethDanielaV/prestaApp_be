package com.example.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prestamos.model.Zona;

public interface ZonaRepository extends JpaRepository<Zona, Integer>{
    
}
