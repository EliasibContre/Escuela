package com.example.escuela.controllers;

import com.example.escuela.dto.grupo.GrupoRequest;
import com.example.escuela.dto.grupo.GrupoResponse;
import com.example.escuela.services.grupo.GrupoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grupos")
@Tag(name= "Api grupos" ,description = "Metodos para grupos")
public class GrupoController extends  CrudController<GrupoRequest, GrupoResponse, GrupoService>{
    public GrupoController(GrupoService service){
        super(service);
    }
}
