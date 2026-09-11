package com.example.escuela.dto.datos;

public record DatosInscripcion(
        DatosAlumno alumno,
        DatosGrupo grupo,
        String fechaInscripcion

) {
}
