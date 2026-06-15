package com.example.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prestamos.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer> {


}
