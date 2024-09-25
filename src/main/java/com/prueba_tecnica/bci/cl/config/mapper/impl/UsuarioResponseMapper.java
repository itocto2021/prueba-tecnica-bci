package com.prueba_tecnica.bci.cl.config.mapper.impl;

import com.prueba_tecnica.bci.cl.config.mapper.generic.IGenericMapper;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioResponse;
import com.prueba_tecnica.bci.cl.domain.entity.UsuarioBO;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Clase responsable de realizar la conversión entre los objetos de respuesta de usuario
 * y los objetos de negocio (BO) relacionados con los usuarios.
 * <p>
 * Esta clase implementa la interfaz {@link IGenericMapper} para proporcionar métodos de mapeo
 * específicos entre {@link UsuarioResponse} y {@link UsuarioBO}.
 * </p>
 *
 *
 * @version 1.0
 * @since 25/09/2024
 * @author itocto
 */
@Component
public class UsuarioResponseMapper implements IGenericMapper<UsuarioResponse, UsuarioBO> {

    /**
     * Convierte un objeto {@link UsuarioBO} a un objeto {@link UsuarioResponse}.
     *
     * @param bo El objeto de negocio a convertir.
     * @return El objeto de respuesta correspondiente, o null si el objeto de negocio es null.
     */
    @Override
    public UsuarioResponse toDTO(UsuarioBO bo) {
        if (Objects.isNull(bo))
            return null;

        String id = Objects.isNull(bo.getId()) ? null : bo.getId().toString();
        String created = Objects.isNull(bo.getCreated()) ? null : bo.getCreated().toString();
        String modified = Objects.isNull(bo.getModified()) ? null : bo.getModified().toString();
        String lastLogin = Objects.isNull(bo.getLastLogin()) ? null : bo.getLastLogin().toString();
        String token = Objects.isNull(bo.getToken()) ? null : bo.getToken();
        String isActive = Objects.isNull(bo.getIsActive()) ? null : bo.getIsActive().toString();

        return UsuarioResponse.builder()
                .id(id)
                .created(created)
                .modified(modified)
                .lastLogin(lastLogin)
                .token(token)
                .isActive(isActive)
                .build();
    }

}

