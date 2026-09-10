package com.example.escuela.dto.aula;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "informacion de las aulas")
public record AulaResponse(
        @Schema(description = "id del aula", example = "1")
        Long id,
        @Schema(description = "nombre del aula", example = "Aula 101")
        String nombre,
        @Schema (description = "capacidad del aula", example = "20")
        Integer capacidad
) { }
