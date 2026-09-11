package com.example.escuela.dto.calificacion;

import com.example.escuela.dto.datos.DatosInscripcion;

import java.math.BigDecimal;

public record CalificacionResponse(
        Long id,
        DatosInscripcion inscripcion,
        BigDecimal calificacion,
        String fechaRegistro

) {
}
