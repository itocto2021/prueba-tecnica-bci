package com.prueba_tecnica.bci.cl.service.generic;

import com.prueba_tecnica.bci.cl.domain.dto.UsuarioDTO;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioResponse;
import com.prueba_tecnica.bci.cl.exception.UsuarioException;

public interface IUsuarioServices {
    UsuarioResponse crearUsuario(UsuarioDTO usuarioDTORequest, String token) throws UsuarioException;
}
