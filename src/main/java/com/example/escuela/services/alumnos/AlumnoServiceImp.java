package com.example.escuela.services.alumnos;
///logica de datos se puede meter al repositorioo ELORIGEN
import com.example.escuela.dto.alumno.AlumnoRequest;
import com.example.escuela.dto.alumno.AlumnoResponse;
import com.example.escuela.entities.Alumno;
import com.example.escuela.exceptions.EntidadRelacionException;
import com.example.escuela.mappers.AlumnoMapper;
import com.example.escuela.repositories.AlumnoRepository;
import com.example.escuela.repositories.InscripcionRepository;
import com.example.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AlumnoServiceImp implements AlumnoService{

    private final AlumnoRepository alumnoRepository;
    private final InscripcionRepository inscripcionRepository;
    private final AlumnoMapper alumnoMapper;

    @Override
    @Transactional
    public List<AlumnoResponse> listar() {
        log.info("Listando alumnos");

        return alumnoRepository.findAll().stream()
                .map(alumnoMapper::entidadAResponse).toList();
    }

    @Override
    public AlumnoResponse obtenerPorId(Long id) {
        return alumnoMapper.entidadAResponse(obtenerAlumno(id));
    }

    @Override
    public AlumnoResponse registrar(AlumnoRequest request) {
        Alumno alumno = alumnoMapper.requestAEntidad(request, generarEmail(request), generarMatricula(request));
        alumnoRepository.save(alumno);
        log.info("Nuevo alumno {} registrado correctamente", alumno.getNombre());
        return alumnoMapper.entidadAResponse(alumno);
    }

    @Override
    public AlumnoResponse actualizar(AlumnoRequest request, Long id) {
        Alumno alumno= obtenerAlumno(id);
        log.info("actualizando alumno con id: {}", id);
        if (alumno.cambioEnDatos(
                request.nombre().trim(),
                request.apellidoPaterno().trim(),
                request.apellidoMaterno().trim()
        )){
            alumno.actualizar(
                    request.nombre(),
                    request.apellidoPaterno(),
                    request.apellidoMaterno(),
                    generarEmail(request),
                    generarMatricula(request));
        };
        return alumnoMapper.entidadAResponse(alumno);
    }

    @Override
    public void eliminar(Long id) {
        Alumno alumno =obtenerAlumno(id);
        log.info("eliminadndo alumno con id: {}", id);
        if (inscripcionRepository.existsByAlumnoId(id))
            throw new EntidadRelacionException(
                    "No se puede eliminar ya que tiene inscripciones asignadas");
        log.info("alumno con id: {} elimando correctamente ", id);
    }

    private Alumno obtenerAlumno(Long id){
        return ServiceUtils.obtenerEntidadOException(alumnoRepository,id,Alumno.class);
    }

    private String generarEmail(AlumnoRequest request){

        log.info("Generando Email");

        return alumnoRepository.generarEmail(
                request.nombre().trim(),
                request.apellidoPaterno().trim(),
                request.apellidoMaterno().trim());
    }
    private String generarMatricula(AlumnoRequest request){

        log.info("Generando matricula");

        return alumnoRepository.generarMatricula(
                request.nombre().trim(),
                request.apellidoPaterno().trim(),
                request.apellidoPaterno().trim());
    }
}


///logica de datos se puede meter al repositorioo