package com.example.escuela.repositories;

import com.example.escuela.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    @Query(nativeQuery = true, value = "SELECT GENERAR_MATRICULA(:nombre, :paterno, :materno)FROM DUAL")
    String generarMatricula(
            @Param("nombre") String nombre,
            @Param("paterno") String apellidoPaterno,
            @Param("materno") String apelledoMaterno
    );
    @Query(nativeQuery = true, value = "SELECT GENERAR_EMAIL(:nombre, :paterno, :materno)FROM DUAL")
    String generarEmail(
            @Param("nombre") String nombre,
            @Param("paterno") String apellidoPaterno,
            @Param("materno") String apelledoMaterno
    );
}
