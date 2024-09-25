package com.prueba_tecnica.bci.cl.controller;

import com.prueba_tecnica.bci.cl.domain.dto.UsuarioDTO;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioResponse;
import com.prueba_tecnica.bci.cl.exception.UsuarioException;
import com.prueba_tecnica.bci.cl.service.generic.IUsuarioServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * Controlador REST para manejar las solicitudes relacionadas con los usuarios.
 * Esta clase expone endpoints para registrar nuevos usuarios y obtener la lista
 * de usuarios existentes en el sistema.
 * <p>
 * Utiliza el servicio {@link IUsuarioServices} para realizar las operaciones
 * necesarias en la gestión de usuarios.
 * </p>
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final IUsuarioServices usuarioService;


    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * @param usuarioRequest Objeto que contiene la información del nuevo usuario a registrar.
     * @param authHeader El encabezado de autorización que puede contener un token JWT.
     * @return Un objeto {@link ResponseEntity} que contiene la respuesta con la información del usuario registrado
     *         y un código de estado 201 (CREATED) si la operación es exitosa, o un mensaje de error
     *         con estado 400 (BAD REQUEST) si el correo ya está registrado.
     */
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

    /**
     * Endpoint para obtener la lista de todos los usuarios registrados en el sistema.
     *
     * @return Un objeto {@link ResponseEntity} que contiene la lista de usuarios y un código de estado 200 (OK).
     * @throws UsuarioException Si ocurre un error al intentar obtener la lista de usuarios.
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> obtenerUsuarios() throws UsuarioException {
        return new ResponseEntity<>(usuarioService.obtenerUsuarios(),HttpStatus.OK);
    }
}
