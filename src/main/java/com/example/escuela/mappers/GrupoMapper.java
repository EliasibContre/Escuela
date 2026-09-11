package com.example.escuela.mappers;

import com.example.escuela.dto.datos.DatosAula;
import com.example.escuela.dto.datos.DatosCurso;
import com.example.escuela.dto.datos.DatosMaestro;
import com.example.escuela.dto.grupo.GrupoRequest;
import com.example.escuela.dto.grupo.GrupoResponse;
import com.example.escuela.entities.Aula;
import com.example.escuela.entities.Curso;
import com.example.escuela.entities.Grupo;
import com.example.escuela.entities.Maestro;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GrupoMapper implements CommonMapper<GrupoRequest, GrupoResponse,Grupo>{

    private final CursoMapper cursoMapper;

    @Override
    public Grupo requestAEntidad(GrupoRequest request) {
        if (request==null)return null;
        return Grupo.builder()
                .periodo(request.periodo())
                .build();
    }

    public Grupo requestAEntidad(GrupoRequest request, Curso curso, Maestro maestro, Aula aula){
        if (request==null)return null;
        Grupo grupo = requestAEntidad(request);
        grupo.actualizar(
                curso,
                maestro,
                aula,
                request.periodo());
        return grupo;
    }
@Override
    public GrupoResponse entidadAResponse(Grupo entidad){

        if (entidad==null)return null;
        DatosCurso curso= cursoMapper.enditadADatosCurso(entidad.getCurso());
        Maestro maestro=entidad.getMaestro();
        String nombreCompleto=String.join(" ",
                maestro.getNombre(),
                maestro.getApellidoPaterno(),
                maestro.getApellidoMaterno());
        DatosMaestro datosMaestro = new DatosMaestro(
                nombreCompleto,
                maestro.getEmail(),
                maestro.getTelefono());
        Aula aula=entidad.getAula();
        DatosAula datosAula=new DatosAula(
                aula.getNombre(),
                aula.getCapacidad());
        /*
        * getHOrarios obtiene los horarios del grupo
        * stream procesa los elementos de la coleccion
        * map transforma cada horario en un texto
        * toList reune los textos en una lista
        * */
        List<String> horarios=entidad.getHorarios().stream()
                .map(horario ->
                        horario.getDiaSemana().getDescripcion() + " "
                + horario.getHoraInicio() + " - " + horario.getHoraFin())
                .toList();

        return new GrupoResponse(
                entidad.getId(),
                curso,
                datosMaestro,
                datosAula,
                horarios,
                entidad.getPeriodo());
    }

}
