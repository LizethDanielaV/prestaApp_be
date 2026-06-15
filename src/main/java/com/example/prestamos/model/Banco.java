package com.example.prestamos.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "banco")
@Data
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_banco;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private boolean activo;

    @OneToMany(mappedBy = "banco")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<MetodoDePago> metodosDePago;
}
