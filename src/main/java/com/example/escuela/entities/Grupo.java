package com.example.escuela.entities;


import com.example.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GRUPOS", uniqueConstraints = @UniqueConstraint(
        name = "GRUPO_CU_MA_AU_PE_UK",
        columnNames = {"ID_CURSO","ID_MAESTRO","ID_AULA","PERIODO"}
))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GRUPO")
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CURSO",nullable = false)
    private Curso curso;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MAESTRO",nullable = false)
    private Maestro maestro;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AULA",nullable = false)
    private Aula aula;
    @Column(name = "PERIODO",  nullable = false, length = 20)
    private String periodo;

    @Builder.Default
    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY)
    private List<Horario> horarios = new ArrayList<>();

    private void validarDatos( Curso curso, Maestro maestro, Aula aula, String periodo){
        if (curso==null)
            throw new IllegalArgumentException("El curso es obligatorio");
        if (maestro==null)
            throw new IllegalArgumentException("Maestro es obligatorio");
        if (aula==null)
            throw new IllegalArgumentException("Aula es obligatorio");
        StringCustomUtils.validarTamanio(periodo,1,20,"EL PERIODO ES REQUERIDO Y DEBE DE SER ENTRE 1 Y 20 CARACTERES");

    }
    public void actualizar (Curso curso, Maestro maestro, Aula aula, String periodo){
        validarDatos(curso, maestro, aula, periodo);
        this.curso=curso;
        this.maestro=maestro;
        this.aula=aula;
        this.periodo=periodo.trim();

    }
}
