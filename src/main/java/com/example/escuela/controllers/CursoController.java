package com.example.escuela.controllers;

import com.example.escuela.dto.curso.CursoRequest;
import com.example.escuela.dto.curso.CursoResponse;
import com.example.escuela.services.curso.CursoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cursos")
@Tag(name= "Api Cursos" ,description = "Metodos para cursos")
public class CursoController extends CrudController<CursoRequest, CursoResponse, CursoService>{
    public CursoController(CursoService service){
        super(service);
    }
}
