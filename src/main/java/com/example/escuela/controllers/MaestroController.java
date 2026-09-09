package com.example.escuela.controllers;

import com.example.escuela.dto.maestro.MaestroRequest;
import com.example.escuela.dto.maestro.MaestroResponse;
import com.example.escuela.services.maestros.MaestroServices;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maestros")
@Tag(name= "Api Maestros" ,description = "Metodos para maestros")
public class MaestroController extends CrudController<MaestroRequest, MaestroResponse, MaestroServices>{

    public MaestroController(MaestroServices services){
        super(services);
    }
}
