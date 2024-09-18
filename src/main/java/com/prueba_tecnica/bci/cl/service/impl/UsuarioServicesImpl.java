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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServicesImpl implements IUsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioResponseMapper responseMapper;


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
