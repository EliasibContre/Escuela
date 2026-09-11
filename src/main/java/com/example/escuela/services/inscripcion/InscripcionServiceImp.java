package com.example.escuela.services.inscripcion;

import com.example.escuela.dto.inscripcion.InscripcionRequest;
import com.example.escuela.dto.inscripcion.InscripcionResponse;
import com.example.escuela.entities.Alumno;
import com.example.escuela.entities.Grupo;
import com.example.escuela.entities.Inscripcion;
import com.example.escuela.exceptions.EntidadRelacionException;
import com.example.escuela.mappers.InscripcionMapper;
import com.example.escuela.repositories.AlumnoRepository;
import com.example.escuela.repositories.CalificacionRepository;
import com.example.escuela.repositories.GrupoRepository;
import com.example.escuela.repositories.InscripcionRepository;
import com.example.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class InscripcionServiceImp implements InscripcionService{
    private final InscripcionRepository inscripcionRepository;
    private final AlumnoRepository alumnoRepository;
    private final GrupoRepository grupoRepository;
    private final CalificacionRepository calificacionRepository;
    private final InscripcionMapper inscripcionMapper;


    @Override
    @Transactional(readOnly = true)
    public List<InscripcionResponse> listar() {
        log.info("Listando inscripciones");
        return inscripcionRepository.findAll().stream()
                .map(inscripcionMapper::entidadAResponse)
                .toList();

    }

    @Override
    @Transactional(readOnly = true)
    public InscripcionResponse obtenerPorId(Long id) {
        log.info("Consultando inscripción con id {}", id);
        return inscripcionMapper.entidadAResponse(
                obtenerInscripcion(id)
        );

    }

    @Override
    public InscripcionResponse registrar(InscripcionRequest request) {
        Alumno alumno = obtenerAlumno(request.idAlumno());
        Grupo grupo = obtenerGrupo(request.idGrupo());

        log.info("Registrando inscripción del alumno {} al grupo {}",
                request.idAlumno(), request.idGrupo());

        if (inscripcionRepository.existsByAlumnoIdAndGrupoId(
                request.idAlumno(),
                request.idGrupo()
        )) {
            throw new IllegalStateException(
                    "El alumno ya está inscrito en ese grupo"
            );
        }

        Inscripcion inscripcion = inscripcionMapper.requestAEntidad(
                request,
                alumno,
                grupo
        );

        Inscripcion guardada = inscripcionRepository.save(inscripcion);

        return inscripcionMapper.entidadAResponse(guardada);

    }

    @Override
    public InscripcionResponse actualizar(InscripcionRequest request, Long id) {
        Inscripcion inscripcion = obtenerInscripcion(id);
        Alumno alumno = obtenerAlumno(request.idAlumno());
        Grupo grupo = obtenerGrupo(request.idGrupo());

        log.info("Actualizando inscripción {}: alumno {}, grupo {}",
                id, request.idAlumno(), request.idGrupo());

        if (inscripcionRepository.existsByAlumnoIdAndGrupoIdAndIdNot(
                request.idAlumno(),
                request.idGrupo(),
                id
        )) {
            throw new IllegalStateException(
                    "Ya existe otra inscripción de ese alumno en ese grupo"
            );
        }

        inscripcion.actualizar(alumno, grupo);

        return inscripcionMapper.entidadAResponse(inscripcion);

    }

    @Override
    public void eliminar(Long id) {
        Inscripcion inscripcion = obtenerInscripcion(id);
        log.info("Solicitando eliminación de inscripción con id {}", id);

        if (calificacionRepository.existsByInscripcionId(id)) {
            throw new EntidadRelacionException(
                    "No se puede eliminar una inscripción con calificación asociada"
            );
        }

        inscripcionRepository.delete(inscripcion);


    }

    private Inscripcion obtenerInscripcion(Long id) {
        return ServiceUtils.obtenerEntidadOException(
                inscripcionRepository,
                id,
                Inscripcion.class
        );
    }

    private Alumno obtenerAlumno(Long id) {
        return ServiceUtils.obtenerEntidadOException(
                alumnoRepository,
                id,
                Alumno.class
        );
    }

    private Grupo obtenerGrupo(Long id) {
        return ServiceUtils.obtenerEntidadOException(
                grupoRepository,
                id,
                Grupo.class
        );
    }

}
