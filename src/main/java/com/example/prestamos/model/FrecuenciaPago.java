package com.example.prestamos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "frecuencia_pago")
@Data
public class FrecuenciaPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_referencia_pago;

    @Column(length = 20, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int dias;

}
