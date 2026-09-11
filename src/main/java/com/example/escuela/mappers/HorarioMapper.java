package com.example.escuela.mappers;

import com.example.escuela.dto.datos.DatosGrupo;
import com.example.escuela.dto.horario.HorarioRequest;
import com.example.escuela.dto.horario.HorarioResponse;
import com.example.escuela.entities.Grupo;
import com.example.escuela.entities.Horario;
import com.example.escuela.entities.Maestro;
import com.example.escuela.enums.DiaSemana;

import org.springframework.stereotype.Component;

@Component

public class HorarioMapper {
    public Horario requestAEntidad(HorarioRequest request, Grupo grupo) {
        if (request == null) {
            return null;
        }

        DiaSemana dia = DiaSemana.obtenerCategoriaPorDescripcion(
                request.dia()
        );

        return Horario.crear(
                grupo,
                dia,
                request.horaInicio(),
                request.horaFin()
        );
    }

    public HorarioResponse entidadAResponse(Horario entidad) {
        if (entidad == null) {
            return null;
        }

        Grupo grupo = entidad.getGrupo();
        Maestro maestro = grupo.getMaestro();

        String nombreCompleto = String.join(" ",
                maestro.getNombre(),
                maestro.getApellidoPaterno(),
                maestro.getApellidoMaterno()
        );

        DatosGrupo datosGrupo = new DatosGrupo(
                grupo.getCurso().getNombre(),
                nombreCompleto,
                grupo.getAula().getNombre(),
                grupo.getPeriodo()
        );

        String horario = entidad.getDiaSemana().getDescripcion()
                + " " + entidad.getHoraInicio()
                + " " + entidad.getHoraFin();

        return new HorarioResponse(
                entidad.getId(),
                datosGrupo,
                horario
        );
    }


}
