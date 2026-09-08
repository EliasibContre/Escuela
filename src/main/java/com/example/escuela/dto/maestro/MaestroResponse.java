package com.example.escuela.dto.maestro;

import com.example.escuela.dto.datos.DatosCurso;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "")
public record MaestroResponse(
        @Schema(description = "id de ejemp.o", example = "1")
        Long id,
        @Schema(description = "nombre completo", example = "maximo decimo meridio")
        String nombre,
        @Schema(description = "email del maestro", example = "maestro@maestr.com" )
        String email,
        @Schema(description = "Telefono del maestro", example = "1234567890")
        String telefono,
        @Schema(description = "datos del curso" )
        List<DatosCurso>cursos

)
{ }
