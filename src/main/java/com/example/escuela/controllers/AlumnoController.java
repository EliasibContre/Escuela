package com.example.escuela.controllers;

import com.example.escuela.dto.alumno.AlumnoRequest;
import com.example.escuela.dto.alumno.AlumnoResponse;
import com.example.escuela.services.alumnos.AlumnoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos")
@Tag(name = "API alumno", description = "metodos para gestionar alumno")
public class AlumnoController  extends CrudController<AlumnoRequest, AlumnoResponse, AlumnoService>{
    public AlumnoController(AlumnoService service){
        super(service);
    }

}
