package com.example.escuela.dto.aula;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para crear o actaulizar un aula")
public record AulaRequest(
        @Schema(description = "nombre del aula", example = "Aula 101")
        @NotBlank(message = "El nombre es requerido")
        @Size(max = 100,message = "El aula debe tener entre 1 y 100 caracteres")
        String nombre,
        @Schema(description = "Capacidad de las aulas ", example = "20")
        @NotNull (message ="La capacidad es requerida")
        @Positive(message = "EL VALOR DEBE SER POSITIVO ")
        Integer capacidad
) {
}
