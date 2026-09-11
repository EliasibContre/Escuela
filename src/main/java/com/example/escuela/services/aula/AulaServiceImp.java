package com.example.escuela.services.aula;

import com.example.escuela.dto.aula.AulaRequest;
import com.example.escuela.dto.aula.AulaResponse;
import com.example.escuela.dto.maestro.MaestroRequest;
import com.example.escuela.entities.Aula;
import com.example.escuela.exceptions.EntidadRelacionException;
import com.example.escuela.mappers.AulaMapper;
import com.example.escuela.repositories.AulaRepository;
import com.example.escuela.repositories.GrupoRepository;
import com.example.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AulaServiceImp implements AulaService {
    private final AulaRepository aulaRepository;
    private final GrupoRepository grupoRepository;
    private final AulaMapper aulaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<AulaResponse> listar() {
        log.info("Listando las aulas ");
        return aulaRepository.findAll().stream()
                .map(aulaMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AulaResponse obtenerPorId(Long id) {
        return aulaMapper.entidadAResponse(obtenerAula(id));
    }

    @Override
    public AulaResponse registrar(AulaRequest request) {
        log.info("registrando aula");
        validarDatosUnicos(request);
        Aula aula = aulaMapper.requestAEntidad(request);
        aulaRepository.save(aula);
        log.info("Aula: {} registrada", aula.getNombre());
        return aulaMapper.entidadAResponse(aula);
    }

    @Override
    public AulaResponse actualizar(AulaRequest request, Long id) {
        Aula aula= obtenerAula(id);
        log.info("Actualizando aula con id {}", id);
        validarCambiosUnicos(request, id);
        aula.actualizar(
                request.nombre(),
                request.capacidad());
        log.info("Aula actualizada con nombre :{}", aula.getNombre());
        return aulaMapper.entidadAResponse(aula);
    }

    @Override
    public void eliminar(Long id) {
        Aula aula=obtenerAula(id);
        log.info("Eliminando aula con di: {}", id);
        if (grupoRepository.existsByAulaId(id))
            throw new EntidadRelacionException("No se puede elimiar el aula porque tiene grupos asignados");
        aulaRepository.delete(aula);
    }

    private Aula obtenerAula(Long id){
        return ServiceUtils.obtenerEntidadOException(aulaRepository, id, Aula.class);
    }
    private void validarDatosUnicos(AulaRequest request){
        String nombre=request.nombre().trim();
        log.info("Validando nombre unico");
        if (aulaRepository.existsByNombreIgnoreCase(nombre))
            throw new IllegalArgumentException("Ya existe un aula con ese nombre registrado"+ nombre);

    }
    private void validarCambiosUnicos(AulaRequest request, Long id){
        log.info("VAlidando que los datos que se cambian sean unicos");
        if (aulaRepository.existsByNombreIgnoreCaseAndIdNot(request.nombre().trim(), id))
            throw new IllegalArgumentException("Existe otra aula con ese nombre" + request.nombre() );

    }
}
