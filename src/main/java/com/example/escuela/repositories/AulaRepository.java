package com.example.escuela.repositories;

import com.example.escuela.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
    boolean existsByNombreIgnoreCaseAndIdNot (String nombre, Long id);

 //   boolean existsByNombreAndIdNot(String nombre, Long id);

    boolean existsByNombreIgnoreCase(String nombre);




}
