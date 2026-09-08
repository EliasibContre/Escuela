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
    private String apellidoPaterno;
    @Column(name = "APELLIDO_MATERNO", nullable = false, length = 50 )
    private String apellidoMaterno;
    @Column(name = "EMAIL", nullable = false, length = 100, unique = true)
    private String email;
    @Column(name = "TELEFONO", nullable = false, length = 10, unique = true)
    private String telefono;

    @Builder.Default
    //optimizar rendimiento, no cargar las relaciones hasta que uno lo pida
    @OneToMany(mappedBy = "maestro", fetch = FetchType.LAZY)
    private List<Grupo> grupos = new ArrayList<>();

    public void validarDatos(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String telefono) {
        StringCustomUtils.validarTamanio(nombre, 1,50, "El nombre es requerido y debe tener entre 1 y 50 carac");
        StringCustomUtils.validarTamanio(apellidoPaterno, 1,50, "El apellido es requerido y debe de ser entre 1 y 50 carac ");
        StringCustomUtils.validarTamanio(apellidoMaterno,1,50,"El apellido es requerido y debe ser entre 1 y 50 carac");
        StringCustomUtils.validarTamanio(email,8,100,"El email es requerido y necesita de 8 a 100 carac");
        StringCustomUtils.validarTamanio(telefono,10,10,"El telefono es requerido y debe tener 10 carac");
    }

    public void actualizar(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String telefono) {


        validarDatos(nombre,apellidoPaterno,apellidoMaterno,email,telefono);

        this.nombre = nombre.trim();
        this.apellidoPaterno = apellidoPaterno.trim();
        this.apellidoMaterno = apellidoMaterno.trim();
        this.email = email.trim();
        this.telefono = telefono.trim();
    }
}
