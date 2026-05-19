package com.example.prestamos.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cuota")
@Data
public class Cuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cuota;

    @Column(nullable = false)
    private int numero_de_cuota;

    @Column(nullable = false)
    private LocalDate fecha_esperada;

    @Column
    private LocalDateTime fecha_pago_real;

    @Column(scale = 2, nullable = false)
    private float monto_capital;

    @Column(scale = 2, nullable = false)
    private float monto_intereses;

    @Column(scale = 2, nullable = false)
    private float monto_total;

    @ManyToOne()
    @JoinColumn(name="estado_id")
    private Estado estado;

    @ManyToOne(optional=false)
    @JoinColumn(name="credito_id")
    private Credito credito;

    @OneToMany(mappedBy = "cuota")
    private List<Abono> abonos;

}
