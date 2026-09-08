package com.example.escuela.dto.alumno;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Datos necesarios para crear o actualizar")
public record AlumnoRequest(
        String nombre,
        String apellidoPaterno,
        String apellidoMaterno
)
{ }
