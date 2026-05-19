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
@Table(name = "estado")
@Data
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_estado;

    @Column(length = 20, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "estado")
    private List<Cuota> cuotas;

}
