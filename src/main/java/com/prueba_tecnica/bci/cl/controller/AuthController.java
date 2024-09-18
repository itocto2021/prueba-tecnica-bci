package com.prueba_tecnica.bci.cl.controller;

import com.prueba_tecnica.bci.cl.config.security.JwtUtil;
import com.prueba_tecnica.bci.cl.domain.dto.AuthRequest;
import com.prueba_tecnica.bci.cl.domain.dto.AuthResponse;
import com.prueba_tecnica.bci.cl.service.generic.IAuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private IAuthServices authServices;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {

        // Autenticación del usuario
        boolean isAuthenticated = authServices.authenticate(authRequest.getUsername(), authRequest.getPassword());

        if (isAuthenticated) {
            // Genera el token JWT
            AuthResponse token = JwtUtil.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}

