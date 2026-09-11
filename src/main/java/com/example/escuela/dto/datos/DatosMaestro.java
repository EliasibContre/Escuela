package com.example.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos del maestro" )
public record DatosMaestro(
        @Schema(description = "Nombre completo del maestro", example = "Jose Mader Rodriguez")
        String nombre,
        @Schema(description = "Correo electronico del maestro", example = "maestro@maestro.com")
        String email,
        @Schema(description = "telefono del maestro", example = "5637033811")
        String telefono
) {
}
