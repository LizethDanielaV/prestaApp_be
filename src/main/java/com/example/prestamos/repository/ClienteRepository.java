package com.example.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prestamos.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
