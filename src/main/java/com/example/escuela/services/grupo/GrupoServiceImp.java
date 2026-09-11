package com.example.escuela.services.grupo;

import com.example.escuela.dto.grupo.GrupoRequest;
import com.example.escuela.dto.grupo.GrupoResponse;

import com.example.escuela.entities.Aula;
import com.example.escuela.entities.Curso;
import com.example.escuela.entities.Grupo;
import com.example.escuela.entities.Maestro;
import com.example.escuela.exceptions.EntidadRelacionException;
import com.example.escuela.mappers.GrupoMapper;
import com.example.escuela.repositories.*;
import com.example.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class GrupoServiceImp implements GrupoService{
    private final GrupoRepository grupoRepository;
    private final CursoRepository cursoRepository;
    private final MaestroRepository maestroRepository;
    private final AulaRepository aulaRepository;
    private final GrupoMapper grupoMapper;
    private final HorarioRepository horarioRepository;
    private final InscripcionRepository inscripcionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<GrupoResponse> listar() {
        log.info("Listando los grupos");
        return grupoRepository.findAll().stream()
                .map(grupoMapper::entidadAResponse).toList();
    }
    @Transactional(readOnly = true)
    @Override
    public GrupoResponse obtenerPorId(Long id) {
        return grupoMapper.entidadAResponse(obtenerGrupo(id));
    }

    @Override
    public GrupoResponse registrar(GrupoRequest request) {
        Curso curso=ServiceUtils.obtenerEntidadOException(
                cursoRepository,request.idCurso(),Curso.class);
        Maestro maestro = ServiceUtils.obtenerEntidadOException(
                maestroRepository, request.idMaestro(),Maestro.class);
        Aula aula=ServiceUtils.obtenerEntidadOException(
                aulaRepository, request.idAula(), Aula.class);
        validarDatosUnicos(request);
        Grupo grupo=grupoMapper.requestAEntidad(
                request, curso, maestro, aula);
        log.info("Registrando grupo: curso {}, maestro {}, aula {}",
                request.idCurso(), request.idMaestro(), request.idAula());
        Grupo guardado= grupoRepository.save(grupo);
        return grupoMapper.entidadAResponse(guardado);
    }

    @Override
    public GrupoResponse actualizar(GrupoRequest request, Long id) {
        Grupo grupo=obtenerGrupo(id);
        Curso curso=ServiceUtils.obtenerEntidadOException(
                cursoRepository,request.idCurso(),Curso.class);
        Maestro maestro = ServiceUtils.obtenerEntidadOException(
                maestroRepository, request.idMaestro(),Maestro.class);
        Aula aula=ServiceUtils.obtenerEntidadOException(
                aulaRepository, request.idAula(), Aula.class);
        validarCambiosUnicos(request,id);
        log.info("Actualizando grupo con id {}", id);
        grupo.actualizar(curso,maestro,aula,request.periodo());
        return grupoMapper.entidadAResponse(grupo);
    }

    @Override
    public void eliminar(Long id) {
        Grupo grupo=obtenerGrupo(id);
        if (horarioRepository.existsByGrupoId(id))
            throw new EntidadRelacionException("EL grupo tiene horarios aasociados, no se puede eliminar");
        if (inscripcionRepository.existsByGrupoId(id))
            throw new EntidadRelacionException("El grupo tiene inscripciones asociadas");
        log.info("Solicitando eliminación de grupo con id {}", id);
        grupoRepository.delete(grupo);

    }
    private Grupo obtenerGrupo(Long id){
        return ServiceUtils.obtenerEntidadOException(grupoRepository, id, Grupo.class);
    }

    private void validarDatosUnicos(GrupoRequest request){
        log.info("validando datos unicos para el registro");
        if (grupoRepository.existsByCursoIdAndMaestroIdAndAulaIdAndPeriodo(
                request.idCurso(),
                request.idMaestro(),
                request.idAula(),
                request.periodo().trim()))
        throw new IllegalArgumentException("Ya existe grupo con esos datos");

    }
    private void validarCambiosUnicos( GrupoRequest request, Long id){
        log.info("validando que datos sean unicos para el cambio");
        if (grupoRepository.existsByCursoIdAndMaestroIdAndAulaIdAndPeriodoAndIdNot(
                request.idCurso(),
                request.idMaestro(),
                request.idAula(),
                request.periodo().trim(),
                id))
        throw new IllegalArgumentException("Ya existe otro grupo con ese curso, maestro, aula y periodo");

    }



}
