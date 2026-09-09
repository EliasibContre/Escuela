package com.example.escuela.exceptions;

import com.example.escuela.dto.CustomErrorResponse;
import com.example.escuela.exceptions.RecursoNoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j

public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponse>handleConstraintViolationException(ConstraintViolationException e){
        log.error("violacion de restriccion : {} ", e.getMessage());
        return ResponseEntity.badRequest()
                .body(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        "violacion de restriccion: " + e.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String mensaje = e.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Error de validación en los datos enviados");
        log.error("Error de validación de argumentos: {}", mensaje);
        return ResponseEntity.badRequest()
                .body(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), mensaje));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Error en la petición: {}", e.getMessage());
        return ResponseEntity.badRequest().body(
                new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage())
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalStateException(IllegalStateException e) {
        log.error("Error en el estado de la petición: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new CustomErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage())
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<CustomErrorResponse> handleNoSuchElementException(NoSuchElementException e) {
        log.warn("No se encontró recurso: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNoResourceFoundException(NoResourceFoundException e) {
        log.warn("No se encontró recurso estático: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<CustomErrorResponse> handleRecursoNoEncontradoException(RecursoNoEncontradoException e) {
        log.warn("No se encontró el recurso: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGeneralException(Exception e) {
        log.error("Error interno del servidor: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Error interno del servidor. Por favor, contacte al administrador."));
    }
    //nueva excepcion para manejar errores en el filtrado de productos
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CustomErrorResponse>
    handlMethodArgumentTYpeMismatchException(MethodArgumentTypeMismatchException exception){
        String mensaje=String.format( "El parametro '%s' tiene un valor invalido: %s",
                exception.getName(),
                exception.getValue());
        log.warn("Parametro con formato incorrecto {}", mensaje);
        return ResponseEntity.badRequest()
                .body(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(),mensaje));

    }

}

