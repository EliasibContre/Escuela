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
    @Column(name = "CALIFICACION", nullable = false,length = 100,unique = true)
    private BigDecimal calificacion;


    @Column(name = "FECHA_REGISTRO",length = 200)
    private LocalDate fechaRegistro =LocalDate.now();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INSCRIPCION", nullable = false, unique = true)
    private Inscripcion inscripcion;
}
