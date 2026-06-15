package com.example.prestamos.model;

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
@Table(name = "pago")
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pago;

    @Column(scale = 2, nullable = false)
    private float monto_total;

    @Column(nullable = false)
    private LocalDateTime fecha_pago;

    @Column(length = 20, nullable = false)
    private String tipo; // "UN_CREDITO" o "TODOS_CREDITOS"

    @Column(length = 255)
    private String notas;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "metodo_de_pago_id")
    private MetodoDePago metodoDePago;

    @OneToMany(mappedBy = "pago")
    private List<Abono> abonos;
}