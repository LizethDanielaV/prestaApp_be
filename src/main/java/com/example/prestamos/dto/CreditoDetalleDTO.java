package com.example.prestamos.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditoDetalleDTO {
    private float totalPrestado;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimite;
    private float interes;
    private int numeroDeCuotas;
    private String frecuenciaPago;
    private float valorInteresPorCuota;
    private int cuotasPagadas;
    private int cuotasVencidas;
    private float totalAbonado;
    private float deudaTotal;
}
