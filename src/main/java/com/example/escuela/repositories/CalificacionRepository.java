package com.example.escuela.repositories;

import com.example.escuela.entities.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    boolean existsByInscripcionId(Long idInscripcion);

    boolean existsByInscripcionIdAndIdNot(
            Long idInscripcion,
            Long id
    );

}
