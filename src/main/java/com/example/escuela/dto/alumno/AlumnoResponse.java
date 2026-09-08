package com.example.escuela.dto.alumno;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
@Schema(description = "informacion de una sucursal")
public record AlumnoResponse(
        Long id,
        String nombre,
        String apPaterno,
        String apMaterno,
        String email,
        String matricula,
        LocalDate fechaIngreso
) {
}
