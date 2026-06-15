package com.example.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditoResumenClienteDTO {
    private int id;
    private String label;
    private float montoPrestamo;
    private float montoRestante;
    private int cuotasPagadas;
    private int totalCuotas;
}
