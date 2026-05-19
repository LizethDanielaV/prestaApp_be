package com.example.prestamos.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long cedula;
    private String nombre;
    private String direccion;
    private Integer zonaId;

    // Si viene null, no se crea referencia
    private ReferenciaDTO referencia;
}
