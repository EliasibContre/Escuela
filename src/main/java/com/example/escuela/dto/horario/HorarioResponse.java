package com.example.escuela.dto.horario;

import com.example.escuela.dto.datos.DatosGrupo;

public record HorarioResponse(
        Long id,
        DatosGrupo grupo,
        String horario

) {
}
