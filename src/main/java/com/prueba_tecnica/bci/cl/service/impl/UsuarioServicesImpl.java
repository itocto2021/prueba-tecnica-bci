package com.prueba_tecnica.bci.cl.service.impl;

import com.prueba_tecnica.bci.cl.config.mapper.impl.UsuarioMapper;
import com.prueba_tecnica.bci.cl.config.mapper.impl.UsuarioResponseMapper;
import com.prueba_tecnica.bci.cl.config.utils.Constantes;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioDTO;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioResponse;
import com.prueba_tecnica.bci.cl.domain.entity.UsuarioBO;
import com.prueba_tecnica.bci.cl.exception.UsuarioException;
import com.prueba_tecnica.bci.cl.repository.UsuarioRepository;
import com.prueba_tecnica.bci.cl.service.generic.IUsuarioServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}
