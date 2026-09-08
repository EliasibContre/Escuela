package com.example.escuela.dto.curso;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta de curso")
public record CursoResponse(
        @Schema(description = "id del curso", example = "1")
    Long id,
        @Schema(description = "nombre del curso", example = "ingles")
    String nombre,
        @Schema(description = "descripcion del curso", example = "aqui pertenece tal y se vera tal cosa")
    String descripcion,
        @Schema(description = "maxima o minima del curso", example = "5")
    Integer creditos

) { }
