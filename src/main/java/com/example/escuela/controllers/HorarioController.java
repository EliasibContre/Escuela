package com.example.escuela.controllers;

import com.example.escuela.dto.horario.HorarioRequest;
import com.example.escuela.dto.horario.HorarioResponse;
import com.example.escuela.services.horario.HorarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/horarios")
@Tag(name= "Api horarios" ,description = "Metodos para horarios")
public class HorarioController extends CrudController<HorarioRequest, HorarioResponse, HorarioService>{
    public HorarioController(HorarioService service){
        super(service);
    }
}
