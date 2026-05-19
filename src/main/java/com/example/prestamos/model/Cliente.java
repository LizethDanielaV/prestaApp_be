package com.example.prestamos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
public class Cliente {

    @Id
    @Column(name = "cedula")
    private Long cedula;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 300, nullable = false)
    private String direccion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "zona_id", nullable = false)
    private Zona zona;

    @ManyToOne(optional = true)
    @JoinColumn(name = "referencia_id")
    private Referencia referencia;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Credito> creditos;

}
