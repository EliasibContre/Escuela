package com.example.escuela.dto.curso;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Datos necesarios registrar un curso")
public record CursoRequest(
        @Schema(description = "nombre del curso", example = "ingles")
        @NotBlank(message = "Es requerido")
        @Size(min = 1,max = 100,message = "el nombre debe de contener entre 1 y 100 caract")
        String nombre,
        @Schema(description = "descripcion del curso", example = "tal cosa ekis de un curso")
        @Size(max =200 ,message = "la descripcion debe de contener maximo 200 caracteres")
        String descripcion,
        @Schema(description = "creditos del curso", example = "5")
        @NotNull(message = "Es requerido")
        @Min(value = 1, message = "Los creditos son minimos a 1")
        @Max(value= 10, message = "Los creditos son maximos a 10")
        Integer creditos
) {
}
