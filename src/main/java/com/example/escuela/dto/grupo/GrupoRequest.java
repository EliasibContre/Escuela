package com.example.escuela.dto.grupo;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para crear o actualizar un grupo")
public record GrupoRequest(
        @Schema(description = "ID de curso ", example = "2")
        @NotNull(message ="El curso es requerido")
        @Positive(message = "El identificador del curso debe ser positivo ")
  Long idCurso,
        @Schema(description = "Id de maestro ", example = "3")
        @NotNull (message ="El maestro es requerido")
        @Positive(message = "El identificador del maestro debe ser positivo ")
  Long idMaestro,
        @Schema(description = "Id de las aulas ", example = "5")
        @NotNull (message ="El Aula es requerido")
        @Positive(message = "El identificador del aula debe ser positivo  ")
  Long idAula,
        @Schema(description = "periodo ", example = "2026-01")
        @NotBlank(message ="El periodo es requerido")
        @Size(message = "El periodo debe abarcar entre 1 y 20 caracteres", min = 1,max =20 )
        String periodo


) { }
