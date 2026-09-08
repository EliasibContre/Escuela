package com.example.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record DatosCurso(
        String nombre,
        String descripcion,
        Integer creditos
) {
}
