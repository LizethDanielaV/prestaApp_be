package com.example.prestamos.dto.cliente;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEditarDTO {

    @Positive(message = "La cedula debe ser un numero positivo")
    @Digits(integer = 10, fraction = 0, message = "La cedula no puede tener mas de 10 digitos")
    private Long cedula;

    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo puede contener letras")
    private String nombre;

    @Size(min = 1, max = 200, message = "La direccion debe tener entre 1 y 200 caracteres")
    private String direccion;

    @Positive(message = "El ID de zona debe ser un numero positivo")
    private Integer zonaId;
}
