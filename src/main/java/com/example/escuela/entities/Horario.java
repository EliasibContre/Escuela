package com.example.escuela.entities;

import com.example.escuela.enums.DiaSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalTime;


@Entity
@Table(name = "HORARIOS" )
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HORARIO")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRUPO", nullable = false)
    private Grupo grupo;

    @Enumerated(EnumType.STRING)
    @Column(name = "DIA",nullable = false)
    private DiaSemana diaSemana;

    @Column (name = "HORA_INICIO", length = 5, nullable = false)
    private String horaInicio;

    @Column(name = "HORA_FIN",length = 5,nullable = false)
    private String horaFin;


    private static void validarDatos(
            Grupo grupo,
            DiaSemana diaSemana,
            String horaInicio,
            String horaFin
    ) {
        if (grupo == null) {
            throw new IllegalArgumentException("El grupo es obligatorio");
        }
        if (diaSemana == null) {
            throw new IllegalArgumentException("El día es obligatorio");
        }
        String formatoHora = "([01][0-9]|2[0-3]):[0-5][0-9]";
        if (horaInicio == null || !horaInicio.matches(formatoHora)) {
            throw new IllegalArgumentException(
                    "La hora de inicio debe tener formato HH:mm"
            );
        }
        if (horaFin == null || !horaFin.matches(formatoHora)) {
            throw new IllegalArgumentException(
                    "La hora de fin debe tener formato HH:mm"
            );
        }

        LocalTime inicio = LocalTime.parse(horaInicio);
        LocalTime fin = LocalTime.parse(horaFin);

        if (!fin.isAfter(inicio)) {
            throw new IllegalArgumentException(
                    "La hora de fin debe ser posterior a la hora de inicio"
            );
        }
    }
    public static Horario crear(
            Grupo grupo,
            DiaSemana diaSemana,
            String horaInicio,
            String horaFin
    ) {
        validarDatos(grupo, diaSemana, horaInicio, horaFin);

        Horario horario = new Horario();
        horario.grupo = grupo;
        horario.diaSemana = diaSemana;
        horario.horaInicio = horaInicio;
        horario.horaFin = horaFin;

        return horario;
    }

    public void actualizar(
            Grupo grupo,
            DiaSemana diaSemana,
            String horaInicio,
            String horaFin
    ) {
        validarDatos(grupo, diaSemana, horaInicio, horaFin);

        this.grupo = grupo;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }





}
