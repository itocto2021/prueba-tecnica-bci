package com.prueba_tecnica.bci.cl.domain.dto;

import com.prueba_tecnica.bci.cl.domain.entity.TelefonoBO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponse {
    private String id;
    private String created;
    private String modified;
    private String lastLogin;
    private String token;
    private String isActive;

}
