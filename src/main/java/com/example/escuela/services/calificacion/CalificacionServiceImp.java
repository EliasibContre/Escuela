package com.example.escuela.services.calificacion;


import com.example.escuela.dto.calificacion.CalificacionRequest;
import com.example.escuela.dto.calificacion.CalificacionResponse;
import com.example.escuela.entities.Calificacion;
import com.example.escuela.entities.Inscripcion;
import com.example.escuela.mappers.CalificacionMapper;
import com.example.escuela.repositories.CalificacionRepository;
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
public class CalificacionServiceImp implements CalificacionService{

    private final CalificacionRepository calificacionRepository;
    private final InscripcionRepository inscripcionRepository;
    private final CalificacionMapper calificacionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CalificacionResponse> listar() {
        log.info("Listando calificaciones");
        return calificacionRepository.findAll().stream()
                .map(calificacionMapper::entidadAResponse)
                .toList();

    }

    @Override
    @Transactional(readOnly = true)
    public CalificacionResponse obtenerPorId(Long id) {

        return calificacionMapper.entidadAResponse(
                obtenerCalificacion(id)
        );

    }

    @Override
    public CalificacionResponse registrar(CalificacionRequest request) {
        Inscripcion inscripcion = obtenerInscripcion(
                request.idInscripcion()
        );
        log.info("Registrando calificación para la inscripción {}",
                request.idInscripcion());

        if (calificacionRepository.existsByInscripcionId(
                request.idInscripcion()
        )) {
            throw new IllegalStateException(
                    "La inscripción ya tiene una calificación registrada"
            );
        }

        Calificacion calificacion =
                calificacionMapper.requestAEntidad(request, inscripcion);

        Calificacion guardada =
                calificacionRepository.save(calificacion);

        return calificacionMapper.entidadAResponse(guardada);

    }

    @Override
    public CalificacionResponse actualizar(CalificacionRequest request, Long id) {
        Calificacion calificacion = obtenerCalificacion(id);
        Inscripcion inscripcion = obtenerInscripcion(
                request.idInscripcion()
        );
        log.info("Actualizando calificación {} para la inscripción {}",
                id, request.idInscripcion());

        if (calificacionRepository.existsByInscripcionIdAndIdNot(
                request.idInscripcion(), id
        )) {
            throw new IllegalStateException(
                    "La inscripción ya tiene otra calificación registrada"
            );
        }

        calificacion.actualizar(
                inscripcion,
                request.calificacion()
        );

        return calificacionMapper.entidadAResponse(calificacion);

    }

    @Override
    public void eliminar(Long id) {
        Calificacion calificacion = obtenerCalificacion(id);
        log.info("Solicitando eliminación de calificación con id {}", id);
        calificacionRepository.delete(calificacion);


    }

    private Calificacion obtenerCalificacion(Long id) {
        return ServiceUtils.obtenerEntidadOException(
                calificacionRepository,
                id,
                Calificacion.class
        );
    }

    private Inscripcion obtenerInscripcion(Long id) {
        return ServiceUtils.obtenerEntidadOException(
                inscripcionRepository,
                id,
                Inscripcion.class
        );
    }

}
