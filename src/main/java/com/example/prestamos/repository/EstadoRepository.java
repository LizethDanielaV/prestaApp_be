package com.example.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prestamos.model.Estado;
import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

     Optional<Estado> findByNombreAndAplicaA(String nombre, String aplicaA);

}
