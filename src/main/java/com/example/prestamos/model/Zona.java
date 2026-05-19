package com.example.prestamos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "zona")
@Data //para que lombok me genere automaticamente los getters y lo setters
public class Zona {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY) //para que se generen automaticamente
    private int id_zona;

    @Column(nullable = false)
    private String nombre; 
}
