package com.example.escuela.controllers;

import com.example.escuela.dto.aula.AulaRequest;
import com.example.escuela.dto.aula.AulaResponse;
import com.example.escuela.services.aula.AulaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aulas")
@Tag(name= "Api Aulas" ,description = "Metodos para aulas")
public class AulaController extends CrudController<AulaRequest, AulaResponse, AulaService>{
    public AulaController(AulaService services){
        super(services);
    }

}
