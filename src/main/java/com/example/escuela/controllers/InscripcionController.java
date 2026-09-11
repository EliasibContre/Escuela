package com.example.escuela.controllers;

import com.example.escuela.dto.inscripcion.InscripcionRequest;
import com.example.escuela.dto.inscripcion.InscripcionResponse;
import com.example.escuela.services.inscripcion.InscripcionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inscripciones")
@Tag(
        name = "Api inscripciones",
        description = "Administración de inscripciones de alumnos a grupos"
)

public class InscripcionController extends CrudController<InscripcionRequest, InscripcionResponse, InscripcionService>{
    public InscripcionController(InscripcionService service){
        super(service);
    }
}
