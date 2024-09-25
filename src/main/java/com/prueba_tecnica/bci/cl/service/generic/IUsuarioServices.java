package com.prueba_tecnica.bci.cl.service.generic;

import com.prueba_tecnica.bci.cl.domain.dto.UsuarioDTO;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioResponse;
import com.prueba_tecnica.bci.cl.exception.UsuarioException;

import java.util.List;
import java.util.UUID;

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

    /**
     * Obtiene un usuario específico por su ID.
     *
     * @param id El ID del usuario a obtener.
     * @return Un objeto {@link UsuarioDTO} que representa al usuario solicitado.
     * @throws UsuarioException Si ocurre un error al intentar obtener el usuario.
     */
    UsuarioDTO obtenerUsuarioPorId(UUID id) throws UsuarioException;

    /**
     * Actualiza la información de un usuario existente en el sistema.
     *
     * @param usuarioDTORequest Objeto que contiene la información actualizada del usuario.
     * @param token El token de autenticación para validar la solicitud.
     * @return Un objeto {@link UsuarioResponse} que contiene información sobre el usuario actualizado.
     * @throws UsuarioException Si ocurre un error durante la actualización del usuario.
     */
    UsuarioResponse actualizarUsuario(UsuarioDTO usuarioDTORequest, String token) throws UsuarioException;

    /**
     * Elimina un usuario del sistema por su ID.
     *
     * @param id El ID del usuario a eliminar.
     * @throws UsuarioException Si ocurre un error durante la eliminación del usuario.
     */
    void eliminarUsuario(UUID id) throws UsuarioException;
}
