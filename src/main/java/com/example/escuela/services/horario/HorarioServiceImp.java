package com.example.escuela.services.horario;

import com.example.escuela.dto.horario.HorarioRequest;
import com.example.escuela.dto.horario.HorarioResponse;
import com.example.escuela.entities.Grupo;
import com.example.escuela.entities.Horario;
import com.example.escuela.mappers.HorarioMapper;
import com.example.escuela.repositories.GrupoRepository;
import com.example.escuela.repositories.HorarioRepository;
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
public class HorarioServiceImp implements HorarioService {

    private final HorarioRepository horarioRepository;
    private final GrupoRepository grupoRepository;
    private final HorarioMapper horarioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<HorarioResponse> listar() {
        log.info("Listando horarios");
        return horarioRepository.findAll().stream()
                .map(horarioMapper::entidadAResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public HorarioResponse obtenerPorId(Long id) {
        return horarioMapper.entidadAResponse(obtenerHorario(id));
    }

    @Override
    public HorarioResponse registrar(HorarioRequest request) {
        Grupo grupo = obtenerGrupo(request.idGrupo());


        Horario horario = horarioMapper.requestAEntidad(request, grupo);
        validarTraslape(horario, null);
        log.info("Registrando horario para el grupo {}", request.idGrupo());
        Horario guardado = horarioRepository.save(horario);
        return horarioMapper.entidadAResponse(guardado);

    }

    @Override
    public HorarioResponse actualizar(HorarioRequest request, Long id) {
        Horario horario = obtenerHorario(id);
        Grupo grupo = obtenerGrupo(request.idGrupo());

        Horario candidato = horarioMapper.requestAEntidad(request, grupo);
        validarTraslape(candidato, id);
        log.info("Actualizando horario {} para el grupo {}",
                id, request.idGrupo());
        horario.actualizar(
                grupo,
                candidato.getDiaSemana(),
                candidato.getHoraInicio(),
                candidato.getHoraFin()
        );

        return horarioMapper.entidadAResponse(horario);

    }

    @Override
    public void eliminar(Long id) {
        Horario horario = obtenerHorario(id);
        log.info("Solicitando eliminación de horario con id {}", id);
        horarioRepository.delete(horario);


    }

    private Horario obtenerHorario(Long id) {
        return ServiceUtils.obtenerEntidadOException(
                horarioRepository, id, Horario.class
        );
    }

    private Grupo obtenerGrupo(Long id) {
        return ServiceUtils.obtenerEntidadOException(
                grupoRepository, id, Grupo.class
        );
    }

    private void validarTraslape(Horario horario, Long idExcluir) {
        Grupo grupo = horario.getGrupo();

        boolean existe = horarioRepository.existeTraslape(
                grupo.getId(),
                grupo.getAula().getId(),
                horario.getDiaSemana(),
                horario.getHoraInicio(),
                horario.getHoraFin(),
                idExcluir
        );

        if (existe) {
            throw new IllegalArgumentException(
                    "El horario se traslapa con otro del mismo grupo o aula"
            );
        }

    }
}
