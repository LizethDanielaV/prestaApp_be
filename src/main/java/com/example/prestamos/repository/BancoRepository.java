package com.example.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.prestamos.model.Banco;

public interface BancoRepository  extends JpaRepository<Banco, Integer> {
    
}