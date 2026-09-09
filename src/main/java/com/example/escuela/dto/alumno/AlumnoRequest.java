package com.example.escuela.dto.alumno;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Schema(description = "Datos necesarios para crear o actualizar")
public record AlumnoRequest(
        @Schema(description = "Nombre de alumno", example = "Himiko")
        @NotBlank(message = "el nombre es requerido")
        @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50")
        String nombre,
        @Schema(description = "Apellido paterno de alumno", example = "Hanazagua")
        @NotBlank(message = "el apellido es requerido")
        @Size(min = 1, max = 50, message = "El apellido debe tener entre 1 y 50")
        String apellidoPaterno,
        @Schema(description = "Apeliido materno de alumno", example = "Rodriguez")
        @NotBlank(message = "el apellido es requerido")
        @Size(min = 1, max = 50, message = "El apellido debe tener entre 1 y 50")
        String apellidoMaterno
)
{ }
