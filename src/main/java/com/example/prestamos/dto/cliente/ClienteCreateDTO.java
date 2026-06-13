package com.example.prestamos.dto.cliente;

import com.example.prestamos.dto.referencia.ReferenciaCreateDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCreateDTO {

    @NotNull(message = "La cédula es obligatoria")
    @Positive(message = "La cédula debe ser un número positivo")
    @Digits(integer = 10, fraction = 0, message = "La cédula no puede tener más de 10 dígitos")
    private Long cedula;

    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo puede contener letras")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede superar 300 caracteres")
    private String direccion;

    @NotNull(message = "El campo zona es obligatorio")
    @Positive(message = "El ID de zona debe ser un número positivo")
    private Integer zonaId;

    // Si viene null, no se crea referencia
    @Valid
    private ReferenciaCreateDTO referencia;

}
