package com.prueba_tecnica.bci.cl.controller;

import com.prueba_tecnica.bci.cl.config.security.JwtUtil;
import com.prueba_tecnica.bci.cl.domain.dto.AuthRequest;
import com.prueba_tecnica.bci.cl.domain.dto.AuthResponse;
import com.prueba_tecnica.bci.cl.service.generic.IAuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para manejar las solicitudes de autenticación de usuarios.
 * Esta clase expone un endpoint para autenticar a los usuarios y generar un token JWT
 * si las credenciales son válidas.
 * <p>
 * Utiliza el servicio {@link IAuthServices} para realizar la autenticación y
 * {@link JwtUtil} para generar el token de acceso.
 * </p>
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final IAuthServices authServices;

    /**
     * Endpoint para autenticar a un usuario.
     *
     * @param authRequest Objeto que contiene las credenciales de autenticación del usuario.
     * @return Un objeto {@link ResponseEntity} que contiene el token JWT si la autenticación es exitosa,
     *         o un mensaje de error con estado 401 si las credenciales son inválidas.
     */
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

