package com.example.escuela.dto.inscripcion;

import com.example.escuela.dto.datos.DatosAlumno;
import com.example.escuela.dto.datos.DatosGrupo;

import java.math.BigDecimal;

public record InscripcionResponse(
        Long id,
        DatosAlumno alumno,
        DatosGrupo grupo,
        BigDecimal calificacion,
        String fechaInscripcion
) {
}
