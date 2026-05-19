package com.example.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteResumenZonaDTO {
    private Long cedula;
    private String nombre;
    private String direccion;
    private int cantidadCreditos;
    private double montoTotalPrestamo;
    private double saldoPendiente;
}
