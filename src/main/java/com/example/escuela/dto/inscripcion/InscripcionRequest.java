package com.example.escuela.dto.inscripcion;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InscripcionRequest(
        @NotNull(message = "El alumno es obligatorio")
        @Positive(message = "El identificador del alumno debe ser positivo")
        Long idAlumno,

        @NotNull(message = "El grupo es obligatorio")
        @Positive(message = "El identificador del grupo debe ser positivo")
        Long idGrupo

) {
}
