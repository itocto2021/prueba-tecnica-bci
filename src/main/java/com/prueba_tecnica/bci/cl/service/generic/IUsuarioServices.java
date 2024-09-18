package com.prueba_tecnica.bci.cl.service.generic;

import com.prueba_tecnica.bci.cl.domain.dto.UsuarioDTO;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioResponse;
import com.prueba_tecnica.bci.cl.exception.UsuarioException;

import java.util.List;

public interface IUsuarioServices {
    UsuarioResponse crearUsuario(UsuarioDTO usuarioDTORequest, String token) throws UsuarioException;
    List<UsuarioDTO> obtenerUsuarios() throws UsuarioException;
}
