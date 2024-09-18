package com.prueba_tecnica.bci.cl.config.mapper.impl;

import com.prueba_tecnica.bci.cl.config.mapper.generic.IGenericMapper;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioResponse;
import com.prueba_tecnica.bci.cl.domain.entity.UsuarioBO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UsuarioResponseMapper implements IGenericMapper<UsuarioResponse, UsuarioBO> {
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

