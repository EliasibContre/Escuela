package com.example.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos del curso" )
public record DatosCurso(
        @Schema(description = "Nombre del curso", example = "ingles")
        String nombre,
        @Schema(description = "Descripcio del curso", example = "curso de ingles")
        String descripcion,
        @Schema(description = "creditos del curso", example = "6")
        Integer creditos
) {
}
