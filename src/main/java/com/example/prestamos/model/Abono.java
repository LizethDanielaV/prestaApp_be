package com.example.prestamos.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "abono")
@Data
public class Abono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_abono;

    @Column(scale = 2, nullable = false)
    private float capital_abonado;

    @Column(scale = 2, nullable = false)
    private float interes_abonado;

    @Column(scale = 2, nullable = false)
    private float total_abonado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pago_id")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Pago pago;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cuota_id")
    private Cuota cuota;
}
