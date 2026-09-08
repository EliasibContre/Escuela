package com.example.escuela.dto.maestro;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para reguistrar o actualizar un maestro")
public record MaestroRequest(
        @Schema(description = "nombre del maestro", example = "himeko")
        @NotBlank(message = "Es requerido")
        @Size(min = 1,max =50 ,message = "el nombre debe de contener entre 1 y 50 caracteres")
        String nombre,
        @Schema(description = "apellido paterno del maestro", example = "Hanakawa")
        @NotBlank(message = "es requerido")
        @Size(min = 1,max = 50,message = "el apellido debe de contener entre 1 y 50 caracteres")
        String apellidoPaterno,
        @Schema(description = "apellido materno del maestro", example = "Gonzalez")
        @NotBlank(message = "es requerido")
        @Size(min = 1,max = 50,message = "El apellido debe contener entre 1 y 50 caracteres")
        String apellidoMaterno,
        @Schema(description = "email del maestro",example = "HimekoHana@gmail.com")
        @NotBlank(message = "es requerido")
        @Size(min = 8,max =100 ,message = "El email debe contener maximo 100 y minimo 8 caracteres")
        String email,
        @Schema(description = "telefono del maestro", example = "1111111111")
        @NotBlank(message = "Es requerido")
        @Pattern(regexp = "^[0-9]{10}", message = "El telefono debe contener solo 10 digitos")
        String telefono


) {
}
