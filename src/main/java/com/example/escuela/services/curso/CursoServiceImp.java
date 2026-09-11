package com.example.escuela.services.curso;

import com.example.escuela.dto.curso.CursoRequest;
import com.example.escuela.dto.curso.CursoResponse;
import com.example.escuela.entities.Curso;
import com.example.escuela.exceptions.EntidadRelacionException;
import com.example.escuela.mappers.CursoMapper;
import com.example.escuela.repositories.CursoRepository;
import com.example.escuela.repositories.GrupoRepository;
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
public class CursoServiceImp implements CursoService{
    private  final CursoRepository cursoRepository;
    private  final GrupoRepository grupoRepository;
    private final CursoMapper cursoMapper;


    @Override
    @Transactional(readOnly = true)
    public List<CursoResponse> listar() {
        log.info("Listando cursos...");
        return cursoRepository.findAll().stream()
                .map(cursoMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CursoResponse obtenerPorId(Long id) {
        return cursoMapper.entidadAResponse(obtenerCurso(id));
    }

    @Override
    public CursoResponse registrar(CursoRequest request) {
        log.info("registrando nuevo curso");
        validarDatosUnicos(request);
        Curso curso=cursoMapper.requestAEntidad(request);
        cursoRepository.save(curso);
        log.info("curso: {} creado", curso.getNombre() );
        return cursoMapper.entidadAResponse(curso);
    }

    @Override
    public CursoResponse actualizar(CursoRequest request, Long id) {
        Curso curso=obtenerCurso(id);
        log.info("Actualizando curso. Buscando.....");
        validarCambiosUnicos(request, id);
        curso.actualizar(
                request.nombre(),
                request.descripcion(),
                request.creditos());
        log.info("curso actualizada con nombre: {} ", curso.getNombre());
        return cursoMapper.entidadAResponse(curso);
    }

    @Override
    public void eliminar(Long id) {
        Curso curso=obtenerCurso(id);
        log.info("Eliminando curso, espere.....");
        if (grupoRepository.existsByCursoId(id))
            throw new EntidadRelacionException("Este curso tiene grupos asociados, no se puede eliminar");
        cursoRepository.delete(curso);


    }

    private Curso obtenerCurso(Long id){
        return ServiceUtils.obtenerEntidadOException(cursoRepository, id, Curso.class);
    }

    private void validarDatosUnicos(CursoRequest request){
        String nombre=request.nombre().trim();
        log.info("Validando nombre unico");
        if (cursoRepository.existsByNombreIgnoreCase(nombre))
            throw new IllegalArgumentException("Ya existe un curso con ese nombre registrado " + nombre);

    }

    private void validarCambiosUnicos(CursoRequest request, Long id){
        log.info("VAlidando que los datos que se cambian sean unicos");
        if (cursoRepository.existsByNombreIgnoreCaseAndIdNot(request.nombre().trim(), id))
            throw new IllegalArgumentException("Existe oto curso con ese nombre" + request.nombre().trim() );

    }
}
