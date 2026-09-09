package com.example.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
@Schema(description ="datos de una calificacion")
public record DatosCalificacion(
        @Schema(description = "Nombre del curso", example = "ingles")
        String curso,
        @Schema(description = "dataos del periodo", example = "Dic-oct")
        String periodo,
        @Schema(description = "Clificacion", example = "5")
        BigDecimal calificacion
) {
}
