package com.example.escuela.mappers;

import com.example.escuela.dto.curso.CursoRequest;
import com.example.escuela.dto.curso.CursoResponse;
import com.example.escuela.dto.datos.DatosCurso;
import com.example.escuela.entities.Curso;
import org.springframework.stereotype.Component;
//NO OLVIDAD @COMPONENT A LOS MAPPERS

@Component
public class CursoMapper implements  CommonMapper<CursoRequest, CursoResponse, Curso>{
    @Override
    public Curso requestAEntidad(CursoRequest request) {
       if (request== null)return null;
       return Curso.builder()
               .nombre(request.nombre().trim())
               .descripcion(request.descripcion())
               .creditos(request.creditos())
               .build();
    }

    @Override
    public CursoResponse entidadAResponse(Curso entidad) {
        if (entidad==null)return null;
        return new CursoResponse(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getDescripcion()!= null?
                        entidad.getDescripcion(): "Sin descripcion",
                entidad.getCreditos());
    }

    public DatosCurso enditadADatosCurso(Curso entidad){
        if (entidad==null)return null;
        return new DatosCurso(
                entidad.getNombre(),
                entidad.getDescripcion()!= null?
                        entidad.getDescripcion(): "Sin descripcion",
                entidad.getCreditos());
    }
}
