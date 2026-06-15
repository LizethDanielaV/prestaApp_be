package com.example.prestamos.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AbonoCreditoDTO {
    private float monto;
    private LocalDateTime fechaPago;
    private int metodoPagoId;
    private Integer bancoId; // null si el pago fue en efectivo
}