package com.example.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prestamos.model.MetodoDePago;

public interface MetodoDePagoRepository extends JpaRepository<MetodoDePago, Integer> {

}
