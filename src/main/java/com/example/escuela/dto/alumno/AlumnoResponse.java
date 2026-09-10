package com.example.escuela.dto.alumno;

import com.example.escuela.dto.datos.DatosCalificacion;
import com.example.escuela.dto.datos.DatosCurso;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "informacion de un alummno")
public record AlumnoResponse(
        @Schema(description = "id de ejemp.o", example = "1")
        Long id,
        @Schema(description = "nombre completo", example = "maximo decimo meridio")
        String nombre,
        @Schema(description = "email del maestro", example = "maester@test.com" )
        String email,
        @Schema(description = "matricula completo", example = "1234567890")
        String matricula,
        @Schema(description = "Fecha de ingreso del alumno", example = "12/09/2026" )
        String fechaIngreso,
        @Schema(description = "datos de calificacion" )
        List<DatosCalificacion> Calificaciones,
        @Schema(description = "Promedio del alumno", example = "1" )
        BigDecimal promedio

        ){


}
