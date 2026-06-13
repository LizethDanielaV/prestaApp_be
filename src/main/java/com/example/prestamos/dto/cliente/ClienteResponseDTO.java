package com.example.prestamos.dto.cliente;

import com.example.prestamos.dto.referencia.ReferenciaCreateDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {
    
    private Long cedula;
    private String nombre;
    private String direccion;
    private Integer zonaId;
    private ReferenciaCreateDTO referencia;

}
