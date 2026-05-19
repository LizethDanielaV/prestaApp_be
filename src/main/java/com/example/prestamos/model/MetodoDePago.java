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
@Table(name = "metodo_de_pago")
@Data
public class MetodoDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_metodo_de_pago;

    @Column(length = 15, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "metodoDePago")
    private List<Abono> abonos;
}
