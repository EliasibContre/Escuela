package com.example.escuela.mappers;

import com.example.escuela.dto.calificacion.CalificacionRequest;
import com.example.escuela.dto.calificacion.CalificacionResponse;
import com.example.escuela.dto.datos.DatosAlumno;
import com.example.escuela.dto.datos.DatosGrupo;
import com.example.escuela.dto.datos.DatosInscripcion;
import com.example.escuela.entities.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CalificacionMapper {
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Calificacion requestAEntidad(
            CalificacionRequest request,
            Inscripcion inscripcion
    ) {
        if (request == null) {
            return null;
        }

        return Calificacion.crear(
                inscripcion,
                request.calificacion()
        );
    }

    public CalificacionResponse entidadAResponse(
            Calificacion entidad
    ) {
        if (entidad == null) {
            return null;
        }

        Inscripcion inscripcion = entidad.getInscripcion();
        Alumno alumno = inscripcion.getAlumno();
        Grupo grupo = inscripcion.getGrupo();
        Maestro maestro = grupo.getMaestro();

        DatosAlumno datosAlumno = new DatosAlumno(
                String.join(" ",
                        alumno.getNombre(),
                        alumno.getApellidoPaterno(),
                        alumno.getApellidoMaterno()
                ),
                alumno.getMatricula(),
                alumno.getEmail(),
                formatearFecha(alumno.getFechaIngreso())
        );

        DatosGrupo datosGrupo = new DatosGrupo(
                grupo.getCurso().getNombre(),
                String.join(" ",
                        maestro.getNombre(),
                        maestro.getApellidoPaterno(),
                        maestro.getApellidoMaterno()
                ),
                grupo.getAula().getNombre(),
                grupo.getPeriodo()
        );

        DatosInscripcion datosInscripcion = new DatosInscripcion(
                datosAlumno,
                datosGrupo,
                formatearFecha(inscripcion.getFechaInscripcion())
        );

        return new CalificacionResponse(
                entidad.getId(),
                datosInscripcion,
                entidad.getCalificacion(),
                formatearFecha(entidad.getFechaRegistro())
        );
    }

    private String formatearFecha(LocalDate fecha) {
        return fecha == null ? null : fecha.format(FORMATO_FECHA);
    }


}
