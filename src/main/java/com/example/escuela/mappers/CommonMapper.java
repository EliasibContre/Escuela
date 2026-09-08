package com.example.escuela.mappers;

public interface CommonMapper <RQ, RS, E>{
    E requesAEntidad(RQ request);
    RS entidadAResponse(E entidad);
}
