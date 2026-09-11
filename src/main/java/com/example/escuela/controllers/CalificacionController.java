package com.example.escuela.controllers;

import com.example.escuela.dto.calificacion.CalificacionRequest;
import com.example.escuela.dto.calificacion.CalificacionResponse;
import com.example.escuela.services.calificacion.CalificacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calificaciones")
@Tag(
        name = "Api calificaciones",
        description = "Registro y consulta de calificaciones"
)

public class CalificacionController extends CrudController<CalificacionRequest, CalificacionResponse, CalificacionService> {
    public CalificacionController(CalificacionService service){
        super(service);
    }
}
