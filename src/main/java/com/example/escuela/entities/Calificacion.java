package com.example.escuela.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CALIFICACIONES")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter

public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CALIFICACION")
    private Long id;

    @Column(name = "CALIFICACION", nullable = false)
    private BigDecimal calificacion;

    @Builder.Default
    @Column(name = "FECHA_REGISTRO", nullable = false)
    private LocalDate fechaRegistro =LocalDate.now();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INSCRIPCION", nullable = false, unique = true)
    private Inscripcion inscripcion;

    private static void validarDatos(
            Inscripcion inscripcion,
            BigDecimal calificacion
    ) {
        if (inscripcion == null) {
            throw new IllegalArgumentException(
                    "La inscripción es obligatoria"
            );
        }

        if (calificacion == null) {
            throw new IllegalArgumentException(
                    "La calificación es obligatoria"
            );
        }
    }

    public static Calificacion crear(
            Inscripcion inscripcion,
            BigDecimal calificacion
    ) {
        validarDatos(inscripcion, calificacion);

        Calificacion nueva = new Calificacion();
        nueva.inscripcion = inscripcion;
        nueva.calificacion = calificacion;
        nueva.fechaRegistro = LocalDate.now();

        return nueva;
    }

    public void actualizar(
            Inscripcion inscripcion,
            BigDecimal calificacion
    ) {
        validarDatos(inscripcion, calificacion);

        this.inscripcion = inscripcion;
        this.calificacion = calificacion;
    }

}
