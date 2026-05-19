package com.example.prestamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prestamos.model.Credito;

public interface CreditoRepository extends JpaRepository<Credito, Integer> {
    List<Credito> findByClienteCedula(Long cedula);
}
