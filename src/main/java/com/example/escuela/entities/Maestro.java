package com.example.escuela.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MAESTROS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Maestro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAESTRO")
    private Long id;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "APELLIDO_PATERNO", nullable = false, length = 50 )
    private String apPaterno;
    @Column(name = "APELLIDO_MATERNO", nullable = false, length = 50 )
    private String apMaterno;
    @Column(name = "EMAIL", nullable = false, length = 100, unique = true)
    private String email;
    @Column(name = "TELEFONO", nullable = false, length = 10, unique = true)
    private String telefono;

    @Builder.Default
    //optimizar rendimiento, no cargar las relaciones hasta que uno lo pida
    @OneToMany(mappedBy = "maestro", fetch = FetchType.LAZY)
    private List<Grupo> grupos = new ArrayList<>();


}
