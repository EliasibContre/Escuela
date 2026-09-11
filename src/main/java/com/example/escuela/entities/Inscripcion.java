package com.example.escuela.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;



@Entity
@Table(name = "INSCRIPCIONES", uniqueConstraints = @UniqueConstraint(
        name = "INSCRIPCION_ALU_GRU_UK",
        columnNames = {"ID_ALUMNO","ID_GRUPO"}
))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INSCRIPCION")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ALUMNO",nullable = false)
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRUPO",nullable = false)
    private Grupo grupo;

    @Builder.Default
    @Column(name ="FECHA_INSCRIPCION")
    private LocalDate fechaInscripcion =LocalDate.now();

    @OneToOne(mappedBy = "inscripcion")
    private Calificacion calificacion;


    private static void validarDatos(Alumno alumno, Grupo grupo) {
        if (alumno == null) {
            throw new IllegalArgumentException("El alumno es obligatorio");
        }

        if (grupo == null) {
            throw new IllegalArgumentException("El grupo es obligatorio");
        }
    }

    public static Inscripcion crear(Alumno alumno, Grupo grupo) {
        validarDatos(alumno, grupo);

        Inscripcion nueva = new Inscripcion();
        nueva.alumno = alumno;
        nueva.grupo = grupo;
        nueva.fechaInscripcion = LocalDate.now();

        return nueva;
    }

    public void actualizar(Alumno alumno, Grupo grupo) {
        validarDatos(alumno, grupo);

        this.alumno = alumno;
        this.grupo = grupo;
    }




}
