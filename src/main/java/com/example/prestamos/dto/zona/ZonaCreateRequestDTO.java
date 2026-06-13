package com.example.prestamos.dto.zona;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZonaCreateRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
}
