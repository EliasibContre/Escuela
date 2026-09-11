package com.example.escuela.mappers;

import com.example.escuela.dto.datos.DatosAlumno;
import com.example.escuela.dto.datos.DatosGrupo;
import com.example.escuela.dto.inscripcion.InscripcionRequest;
import com.example.escuela.dto.inscripcion.InscripcionResponse;
import com.example.escuela.entities.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class InscripcionMapper {
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Inscripcion requestAEntidad(
            InscripcionRequest request,
            Alumno alumno,
            Grupo grupo
    ) {
        if (request == null) {
            return null;
        }

        return Inscripcion.crear(alumno, grupo);
    }

    public InscripcionResponse entidadAResponse(Inscripcion entidad) {
        if (entidad == null) {
            return null;
        }

        Alumno alumno = entidad.getAlumno();
        Grupo grupo = entidad.getGrupo();
        Maestro maestro = grupo.getMaestro();
        Calificacion calificacion = entidad.getCalificacion();

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

        return new InscripcionResponse(
                entidad.getId(),
                datosAlumno,
                datosGrupo,
                calificacion == null
                        ? null
                        : calificacion.getCalificacion(),
                formatearFecha(entidad.getFechaInscripcion())
        );
    }

    private String formatearFecha(LocalDate fecha) {
        return fecha == null ? null : fecha.format(FORMATO_FECHA);
    }

}
