package com.example.escuela.dto.horario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Schema
public record HorarioRequest(
        @NotNull(message = "El grupo es obligatorio")
        @Positive(message = "El identificador del grupo debe ser positivo")
        Long idGrupo,

        @NotBlank(message = "El día es obligatorio")
        String dia,

        @NotBlank(message = "La hora de inicio es obligatoria")
        @Pattern(
                regexp = "([01][0-9]|2[0-3]):[0-5][0-9]",
                message = "La hora de inicio debe tener formato HH:mm, entre 00:00 y 23:59"
        )
        String horaInicio,

        @NotBlank(message = "La hora de fin es obligatoria")
        @Pattern(
                regexp = "([01][0-9]|2[0-3]):[0-5][0-9]",
                message = "La hora de fin debe tener formato HH:mm, entre 00:00 y 23:59"
        )
        String horaFin
) {

}
