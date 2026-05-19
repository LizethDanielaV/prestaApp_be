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

    @Column(nullable = false)
    private int numero_de_cuota;

    @Column(nullable = false)
    private LocalDateTime fecha_pago;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal monto_total;

    @ManyToOne(optional = false)
    @JoinColumn(name = "metodo_de_pago_id")
    private MetodoDePago metodoDePago;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cuota_id")
    private Cuota cuota;
}
