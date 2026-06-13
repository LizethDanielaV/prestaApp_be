package com.example.prestamos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "referencia")
@Data
public class Referencia {

    @Id
    @Column(name = "cedula")
    private Long cedula;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(name = "celular")
    private Long celular;

}
