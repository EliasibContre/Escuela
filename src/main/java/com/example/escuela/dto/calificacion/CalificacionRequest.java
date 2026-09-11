package com.example.escuela.dto.calificacion;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CalificacionRequest(
        @NotNull(message = "La inscripción es obligatoria")
        @Positive(message = "El identificador de inscripción debe ser positivo")
        Long idInscripcion,

        @NotNull(message = "La calificación es obligatoria")
        BigDecimal calificacion

) {
}
