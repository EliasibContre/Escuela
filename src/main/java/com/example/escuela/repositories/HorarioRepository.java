package com.example.escuela.repositories;

import com.example.escuela.entities.Horario;
import com.example.escuela.enums.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HorarioRepository extends JpaRepository<Horario, Long > {
    boolean existsByGrupoId(Long idGroup);
    @Query("""
              SELECT COUNT(h) > 0
              FROM Horario h
              WHERE h.diaSemana = :dia
                AND (h.grupo.id = :idGrupo OR h.grupo.aula.id = :idAula)
                AND h.horaInicio < :horaFin
                AND h.horaFin > :horaInicio
                AND (:idExcluir IS NULL OR h.id <> :idExcluir)
              """)
    boolean existeTraslape(
            @Param("idGrupo") Long idGrupo,
            @Param("idAula") Long idAula,
            @Param("dia") DiaSemana dia,
            @Param("horaInicio") String horaInicio,
            @Param("horaFin") String horaFin,
            @Param("idExcluir") Long idExcluir
    );

}

