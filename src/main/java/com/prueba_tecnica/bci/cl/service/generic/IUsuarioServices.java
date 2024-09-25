package com.prueba_tecnica.bci.cl.service.generic;

import com.prueba_tecnica.bci.cl.domain.dto.UsuarioDTO;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioResponse;
import com.prueba_tecnica.bci.cl.exception.UsuarioException;

import java.util.List;

/**
 * Esta interfaz define los servicios relacionados con la gestión de usuarios en la aplicación.
 * Proporciona métodos para crear nuevos usuarios y obtener la lista de usuarios existentes.
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */

public interface IUsuarioServices {
    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param usuarioDTORequest Objeto que contiene la información del nuevo usuario a crear.
     * @param token El token de autenticación para validar la solicitud.
     * @return Un objeto {@link UsuarioResponse} que contiene información sobre el usuario creado.
     * @throws UsuarioException Si ocurre un error durante la creación del usuario.
     */
    UsuarioResponse crearUsuario(UsuarioDTO usuarioDTORequest, String token) throws UsuarioException;

    /**
     * Obtiene la lista de todos los usuarios registrados en el sistema.
     *
     * @return Una lista de objetos {@link UsuarioDTO} que representan a los usuarios existentes.
     * @throws UsuarioException Si ocurre un error al intentar obtener la lista de usuarios.
     */
    List<UsuarioDTO> obtenerUsuarios() throws UsuarioException;
}
