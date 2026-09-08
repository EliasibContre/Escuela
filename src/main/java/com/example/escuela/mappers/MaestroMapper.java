package com.example.escuela.mappers;

import com.example.escuela.dto.datos.DatosCurso;
import com.example.escuela.dto.maestro.MaestroRequest;
import com.example.escuela.dto.maestro.MaestroResponse;
import com.example.escuela.entities.Grupo;
import com.example.escuela.entities.Maestro;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MaestroMapper implements  CommonMapper<MaestroRequest, MaestroResponse, Maestro>{


    private  final CursoMapper cursoMapper;
    @Override
    public Maestro requesAEntidad(MaestroRequest request) {
        if (request == null) return null;
        return Maestro.builder()
                .nombre(request.nombre().trim())
                .apellidoPaterno(request.apellidoPaterno().trim())
                .apellidoMaterno(request.apellidoMaterno().trim())
                .email(request.email().toLowerCase())
                .telefono(request.telefono().trim())
                .build();
    }

    @Override
    public MaestroResponse entidadAResponse(Maestro entidad) {
        if (entidad == null)return null;

        List<DatosCurso> cursos=entidadDatosCursos(entidad);

        return new MaestroResponse(
                entidad.getId(),
                String.join("",
                        entidad.getNombre(),
                        entidad.getApellidoPaterno(),
                        entidad.getApellidoMaterno()),
                entidad.getEmail(),
                entidad.getTelefono(),
                cursos);
    }
    private List<DatosCurso> entidadDatosCursos(Maestro entidad){
        if (entidad==null) return List.of();
        return entidad.getGrupos().stream()
        .map(Grupo::getCurso)
                .map(cursoMapper::enditadADatosCurso)
                .toList();
    }
}
