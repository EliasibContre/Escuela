package com.example.escuela.services.alumnos;

import com.example.escuela.dto.alumno.AlumnoRequest;
import com.example.escuela.dto.alumno.AlumnoResponse;
import com.example.escuela.services.CrudServices;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoService extends CrudServices<AlumnoRequest, AlumnoResponse> {

}
