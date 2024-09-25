package com.prueba_tecnica.bci.cl.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase de manejo global de excepciones para la aplicación.
 *
 * <p>
 * Esta clase captura excepciones específicas lanzadas por el controlador, permitiendo
 * devolver respuestas consistentes y manejadas para los errores que ocurren durante
 * la validación de las solicitudes.
 * </p>
 *
 * @version 1.0
 * @since 25/09/2024
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de validación lanzadas por el framework.
     *
     * @param ex La excepción lanzada cuando hay un error de validación.
     * @return Una respuesta que contiene mensajes de error con un código de estado BAD_REQUEST.
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorResponse.put("mensaje", error.getDefaultMessage());
        });
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
