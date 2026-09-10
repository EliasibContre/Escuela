package com.example.escuela.entities;


import com.example.escuela.utils.StringCustomUtils;
import com.example.escuela.utils.ValoresNumericosUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "CURSOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter

public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CURSO")
    private Long id;
    @Column(name = "NOMBRE", nullable = false,length = 100,unique = true)
    private String nombre;
    @Column(name = "DESCRIPCION",length = 200)
    private String descripcion;
    @Column(name = "CREDITOS",nullable = false)
    private Integer creditos;

    @Builder.Default
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Grupo> grupos =new ArrayList<>();

    public void validarDatos(String nombre, String descripcion, Integer creditos) {
        StringCustomUtils.validarTamanio(nombre, 1,100, "El nombre es requerido y debe tener entre 1 y 100 carac");
        if (descripcion != null && descripcion.trim().length()>200)
            throw new IllegalArgumentException("La descripcion debe tener maximo 200 caracteres");
        ValoresNumericosUtils.validarEnteroPositivo(creditos, "la capacidad debe ser mayor a cero");
        if (creditos>10)
            throw new IllegalArgumentException("Los creditos deben ser como maximo 10");
    }
    public void actualizar(String nombre, String descripcion, Integer creditos){
        validarDatos(nombre,descripcion,creditos);
        this.nombre=nombre.trim();
        this.descripcion=descripcion==null ? null :descripcion.trim();
        this.creditos=creditos;
    }



}
