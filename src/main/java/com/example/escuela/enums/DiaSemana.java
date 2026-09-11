package com.example.escuela.enums;

import com.example.escuela.utils.StringCustomUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public enum DiaSemana {

        LUNES("Lunes"),
        MARTES("Martes"),
        MIERCOLES("Miercoles"),
        JUEVES("Jueves"),
        VIERNES("Viernes"),
        SABADO("Sabado");

        private final String descripcion;

        public static DiaSemana obtenerCategoriaPorDescripcion(String descripcion){
                StringCustomUtils.validarNoVacio(descripcion, "la descripcion es requerida");

                String descripcionNormalizada = StringCustomUtils.quitarAcentos(descripcion);

                for (DiaSemana categoria : values()){
                        if(StringCustomUtils.quitarAcentos(categoria.descripcion).equalsIgnoreCase(descripcionNormalizada))
                                return categoria;
                }
                throw new IllegalArgumentException(
                        "Día no válido: " + descripcion
                );

        }

}
