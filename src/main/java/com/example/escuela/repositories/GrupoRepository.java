package com.example.escuela.repositories;

import com.example.escuela.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository  extends JpaRepository<Grupo, Long> {
    boolean existsByMaestroId(Long idMaestro);

}
