package com.example.escuela.dto.grupo;

import com.example.escuela.dto.datos.DatosAula;
import com.example.escuela.dto.datos.DatosCurso;
import com.example.escuela.dto.datos.DatosMaestro;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Datos mostrados de un grupo")
public record GrupoResponse(
        @Schema(description = "ID del grupo", example = "1")
        Long id,
        @Schema(description = "Datos del curso anidados a grupo" )
        DatosCurso curso,
        @Schema(description = "Datos del maestro anidados a grupo" )
        DatosMaestro maestro,
        @Schema(description = "Datos del aula anidados a grupo" )
        DatosAula aula,
        @Schema(description = "Horarios anidados a grupo" )
        List<String>horarios,
        @Schema(description = "Periodo del grupo", example = "2026-01")
        String periodo
) { }
