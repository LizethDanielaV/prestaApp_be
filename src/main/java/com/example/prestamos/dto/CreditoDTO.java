package com.example.prestamos.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreditoDTO {
    private float montoPrestamo;
    private float interes;
    private int numeroDeCuotas;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimite;
    private Long frecuenciaPagoId;
    private Long clienteCedula;
}
