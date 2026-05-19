package com.example.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prestamos.model.Cuota;

public interface CuotaRepository extends JpaRepository<Cuota, Integer> {

}
