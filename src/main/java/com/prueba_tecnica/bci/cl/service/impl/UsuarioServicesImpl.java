package com.prueba_tecnica.bci.cl.service.impl;

import com.prueba_tecnica.bci.cl.config.mapper.impl.UsuarioMapper;
import com.prueba_tecnica.bci.cl.config.mapper.impl.UsuarioResponseMapper;
import com.prueba_tecnica.bci.cl.config.utils.Constantes;
import com.prueba_tecnica.bci.cl.domain.dto.TelefonoDTO;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioDTO;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioResponse;
import com.prueba_tecnica.bci.cl.domain.entity.TelefonoBO;
import com.prueba_tecnica.bci.cl.domain.entity.UsuarioBO;
import com.prueba_tecnica.bci.cl.exception.UsuarioException;
import com.prueba_tecnica.bci.cl.repository.UsuarioRepository;
import com.prueba_tecnica.bci.cl.service.generic.IUsuarioServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementación de los servicios de gestión de usuarios definidos en la interfaz {@link IUsuarioServices}.
 * Esta clase proporciona métodos para crear nuevos usuarios y obtener la lista de usuarios existentes.
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
@RequiredArgsConstructor
@Service
public class UsuarioServicesImpl implements IUsuarioServices {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    private final UsuarioResponseMapper responseMapper;


    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param request Objeto que contiene la información del nuevo usuario a crear.
     * @param token El token de autenticación para validar la solicitud.
     * @return Un objeto {@link UsuarioResponse} que contiene información sobre el usuario creado.
     * @throws UsuarioException Si ocurre un error durante la creación del usuario, como un correo electrónico duplicado.
     */
    @Override
    public UsuarioResponse crearUsuario(UsuarioDTO request, String token) throws UsuarioException {

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new UsuarioException(Constantes.MSG_EXIST_EMAIL);
        }

        UsuarioBO userBo = usuarioMapper.fromDTO(request);
        userBo.setToken(token);
        userBo.setCreated(LocalDateTime.now());
        userBo.setModified(LocalDateTime.now());
        userBo.setLastLogin(LocalDateTime.now());

        UsuarioBO savedUser = usuarioRepository.save(userBo);

        UsuarioResponse response = responseMapper.toDTO(savedUser);

        return response;
    }

    /**
     * Obtiene la lista de todos los usuarios registrados en el sistema.
     *
     * @return Una lista de objetos {@link UsuarioDTO} que representan a los usuarios existentes.
     * @throws UsuarioException Si ocurre un error al intentar obtener la lista de usuarios.
     */
    @Override
    public List<UsuarioDTO> obtenerUsuarios() throws UsuarioException {

        List<UsuarioBO> users = usuarioRepository.findAll();

        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (UsuarioBO user: users) {
            UsuarioDTO dto = usuarioMapper.toDTO(user);
            usuarioDTOS.add(dto);
        }

        return usuarioDTOS;
    }

    /**
     * Obtiene un usuario específico por su ID.
     *
     * @param id El ID del usuario a obtener.
     * @return Un objeto {@link UsuarioDTO} que representa al usuario solicitado.
     * @throws UsuarioException Si ocurre un error al intentar obtener el usuario.
     */
    @Override
    public UsuarioDTO obtenerUsuarioPorId(UUID id) throws UsuarioException {
        UsuarioBO user = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioException(Constantes.MSG_USER_NOT_FOUND));
        return usuarioMapper.toDTO(user);
    }

    /**
     * Actualiza la información de un usuario existente en el sistema.
     *
     * @param usuarioDTORequest Objeto que contiene la información actualizada del usuario.
     * @param token             El token de autenticación para validar la solicitud.
     * @return Un objeto {@link UsuarioResponse} que contiene información sobre el usuario actualizado.
     * @throws UsuarioException Si ocurre un error durante la actualización del usuario.
     */
    @Override
    public UsuarioResponse actualizarUsuario(UsuarioDTO usuarioDTORequest, String token) throws UsuarioException {
        // Recupera el usuario existente utilizando el ID del DTO
        UsuarioBO user = usuarioRepository.findById(UUID.fromString(usuarioDTORequest.getId()))
                .orElseThrow(() -> new UsuarioException(Constantes.MSG_USER_NOT_FOUND));

        // Verifica si el correo electrónico ha cambiado y si es único
        if (!user.getEmail().equals(usuarioDTORequest.getEmail())
                && usuarioRepository.existsByEmail(usuarioDTORequest.getEmail())) {
            throw new UsuarioException(Constantes.MSG_EXIST_EMAIL);
        }

        // Actualiza los campos del usuario
        user.setName(usuarioDTORequest.getName());
        user.setEmail(usuarioDTORequest.getEmail());

        // Si deseas actualizar la contraseña, asegúrate de que la lógica de encriptación esté implementada
        if (usuarioDTORequest.getPassword() != null && !usuarioDTORequest.getPassword().isEmpty()) {
            user.setPassword(usuarioDTORequest.getPassword()); // Método hipotético para encriptar
        }

        // Actualiza los teléfonos
        updatePhones(user, usuarioDTORequest.getPhones());

        // Guarda el usuario actualizado en el repositorio
        UsuarioBO updatedUser = usuarioRepository.save(user);

        // Devuelve la respuesta mapeada
        return responseMapper.toDTO(updatedUser);
    }

    /**
     * Elimina un usuario del sistema por su ID.
     *
     * @param id El ID del usuario a eliminar.
     * @throws UsuarioException Si ocurre un error durante la eliminación del usuario.
     */
    @Override
    public void eliminarUsuario(UUID id) throws UsuarioException {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioException(Constantes.MSG_USER_NOT_FOUND);
        }
        usuarioRepository.deleteById(id);
    }


    // Método para actualizar los teléfonos
    private void updatePhones(UsuarioBO user, List<TelefonoDTO> newPhones) {
        // Limpiar los teléfonos existentes
        user.getPhones().clear();

        // Si hay nuevos teléfonos, los agregamos
        if (newPhones != null) {
            for (TelefonoDTO phone : newPhones) {
                TelefonoBO phoneBO = new TelefonoBO();
                phoneBO.setNumber(phone.getNumber());
                phoneBO.setCitycode(phone.getCitycode());
                phoneBO.setContrycode(phone.getContrycode());
                user.getPhones().add(phoneBO);
            }
        }
    }
}
