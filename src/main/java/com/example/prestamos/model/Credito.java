package com.example.prestamos.model;

import java.time.LocalDate;
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
@Table(name="credito")
@Data
public class Credito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_credito;

    @Column( nullable = false)
    private float monto_prestamo;

     @Column( nullable = false)
    private float interes;

     @Column( nullable = false)
    private int numero_de_coutas;

     @Column( nullable = false)
    private LocalDate fecha_prestamo;

    @Column( nullable = false)
    private LocalDate fecha_limite;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="frecuencia_pago_id")
    private FrecuenciaPago frecuenciaPago;

    @ManyToOne(optional=false)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "credito")
    private List<Cuota> cuotas;
}
