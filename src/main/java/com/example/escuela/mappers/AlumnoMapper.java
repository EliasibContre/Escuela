package com.example.escuela.mappers;

import com.example.escuela.dto.alumno.AlumnoRequest;
import com.example.escuela.dto.alumno.AlumnoResponse;
import com.example.escuela.dto.datos.DatosCalificacion;
import com.example.escuela.entities.Alumno;
import com.example.escuela.utils.StringCustomUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlumnoMapper implements CommonMapper<AlumnoRequest, AlumnoResponse, Alumno>{

    @Override
    public Alumno requestAEntidad(AlumnoRequest request){
        if (request==null)return null;
        return Alumno.builder()
                .nombre(request.nombre().trim())
                .apellidoPaterno(request.apellidoPaterno().trim())
                .apellidoMaterno(request.apellidoMaterno().trim())
                .build();
    }

    public Alumno requestAEntidad(AlumnoRequest request, String email, String matricula){
        if (request== null) return null;
        Alumno alumno = requestAEntidad(request);
        alumno.asignarDatosAcademicos(email, matricula);
        return alumno;
    }
    @Override
    public AlumnoResponse entidadAResponse(Alumno entidad){
        List<DatosCalificacion> calficaciones=entidadDatosCalificacion(entidad);
        if (entidad==null) return null;
        return new AlumnoResponse(
                entidad.getId(),
                String.join("",
                        entidad.getNombre(),
                        entidad.getApellidoPaterno(),
                        entidad.getApellidoMaterno()),
                entidad.getEmail(),
                entidad.getMatricula(),
                StringCustomUtils.localeDateAString(entidad.getFechaIngreso()),
                calficaciones, entidad.calcularPromedio());
    }

    private List<DatosCalificacion> entidadDatosCalificacion(Alumno entidad){
        if (entidad==null || entidad.getInscripciones() == null || entidad.getInscripciones().isEmpty())
            return List.of();
        return entidad.getInscripciones().stream()
                .map(inscripcion -> new DatosCalificacion(
                        inscripcion.getGrupo().getCurso().getNombre(),
                        inscripcion.getGrupo().getPeriodo(),
                        inscripcion.getCalificacion() != null
                                ? inscripcion.getCalificacion().getCalificacion()
                                :null
                )).toList();
    }

}
