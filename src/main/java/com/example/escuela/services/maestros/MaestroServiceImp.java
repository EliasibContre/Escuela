package com.example.escuela.services.maestros;

import com.example.escuela.dto.maestro.MaestroRequest;
import com.example.escuela.dto.maestro.MaestroResponse;
import com.example.escuela.entities.Maestro;
import com.example.escuela.exceptions.EntidadRelacionException;
import com.example.escuela.mappers.MaestroMapper;
import com.example.escuela.repositories.GrupoRepository;
import com.example.escuela.repositories.MaestroRepository;
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
public class MaestroServiceImp implements MaestroServices {
    private final MaestroRepository maestroRepository;
    private final MaestroMapper maestroMapper;
    private final GrupoRepository grupoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MaestroResponse> listar() {
        log.info("Listando maestros");
        return maestroRepository.findAll().stream()
                .map(maestroMapper::entidadAResponse).toList();

    }

    @Override
    @Transactional(readOnly = true)
    public MaestroResponse obtenerPorId(Long id) {
        return maestroMapper.entidadAResponse(obtenerMaestro(id));
    }

    @Override
    public MaestroResponse registrar(MaestroRequest request) {
        log.info("nuevo maestro");
        validarDatosUnicos(request);
        Maestro maestro = maestroMapper.requesAEntidad(request);
        maestroRepository.save(maestro);
        log.info("nuevo maestro: {} registrado", maestro.getNombre());
        return maestroMapper.entidadAResponse(maestro);
    }

    @Override
    public MaestroResponse actualizar(MaestroRequest request, Long id) {
        Maestro maestro =obtenerMaestro(id);
        log.info("actualizando maestro con id: {}",id);
        validarCambiosUnicos(request, id);
        maestro.actualizar(
                request.nombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno(),
                request.email(),
                request.telefono());
        log.info("Maestro {} actualizado correctamebte", maestro.getNombre());
        return maestroMapper.entidadAResponse(maestro);
    }

    @Override
    public void eliminar(Long id) {
        Maestro maestro =obtenerMaestro(id);
        log.info("eliminando maestro con id: {}",id);
        if (grupoRepository.existsByMaestroId(id))
            throw new EntidadRelacionException("No se puede eliminar el maestro ya que tiene grupos asignados");
        maestroRepository.delete(maestro);
    }

    private Maestro obtenerMaestro(Long id){
        return ServiceUtils.obtenerEntidadOException(maestroRepository, id,Maestro.class);
    }
    private void validarDatosUnicos(MaestroRequest request){
        log.info("validando email unico");
        if (maestroRepository.existsByEmailIgnoreCase(request.email().trim()))
            throw new IllegalArgumentException("ya existe un maestro registrado" + request.email());
        log.info("validadon telefono unico");
        if (maestroRepository.existsByTelefono(request.telefono().trim()))
            throw new IllegalArgumentException("Ya existe un maestro con ese telefono"+request.telefono());

    }
    private void validarCambiosUnicos(MaestroRequest request, Long id){
        log.info("validando email unico");
        if (maestroRepository.existsByEmailIgnoreCaseAndIdNot(request.email().trim(), id))
            throw new IllegalArgumentException("ya existe un maestro registrado" + request.email());
        log.info("validadon telefono unico");
        if (maestroRepository.existsByTelefonoAndIdNot(request.telefono().trim(), id))
            throw new IllegalArgumentException("Ya existe un maestro con ese telefono"+request.telefono());

    }
}
