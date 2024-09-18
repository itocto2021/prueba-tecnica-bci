package com.prueba_tecnica.bci.cl.controller;

import com.prueba_tecnica.bci.cl.config.security.JwtUtil;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioDTO;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioResponse;
import com.prueba_tecnica.bci.cl.exception.UsuarioException;
import com.prueba_tecnica.bci.cl.service.generic.IUsuarioServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioServices usuarioService;


    @PostMapping(produces = "application/json")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody UsuarioDTO usuarioRequest,
                                              @RequestHeader(value = "Authorization", required = false) String authHeader) {

        // Extraer el token del encabezado Authorization
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        try {
            UsuarioResponse usuarioResponse = usuarioService.crearUsuario(usuarioRequest, token);
            return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
        } catch (UsuarioException e) {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", "El correo ya registrado"), HttpStatus.BAD_REQUEST);
        }
    }
}
