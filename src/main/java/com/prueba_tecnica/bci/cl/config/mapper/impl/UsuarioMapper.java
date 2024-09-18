package com.prueba_tecnica.bci.cl.config.mapper.impl;

import com.prueba_tecnica.bci.cl.config.mapper.generic.IGenericMapper;
import com.prueba_tecnica.bci.cl.domain.dto.TelefonoDTO;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioDTO;
import com.prueba_tecnica.bci.cl.domain.entity.TelefonoBO;
import com.prueba_tecnica.bci.cl.domain.entity.UsuarioBO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper implements IGenericMapper<UsuarioDTO, UsuarioBO> {
    @Override
    public UsuarioDTO toDTO(UsuarioBO bo) {
        if (Objects.isNull(bo)) {
            return null;
        }

        String name = Objects.isNull(bo.getName()) ? null : bo.getName();
        String email = Objects.isNull(bo.getEmail()) ? null : bo.getEmail();
        String password = Objects.isNull(bo.getPassword()) ? null : bo.getPassword();
        List<TelefonoDTO> phones = Objects.isNull(bo.getPhones()) ? null : toTelefonoDTOList(bo.getPhones());

        return UsuarioDTO.builder()
                .name(name)
                .email(email)
                .password(password)
                .phones(phones)
                .build();
    }

    @Override
    public UsuarioBO fromDTO(UsuarioDTO dto) {
        String name = Objects.isNull(dto.getName()) ? null : dto.getName();
        String email = Objects.isNull(dto.getEmail()) ? null : dto.getEmail();
        String password = Objects.isNull(dto.getPassword()) ? null : dto.getPassword();
        List<TelefonoBO> phones = Objects.isNull(dto.getPhones()) ? null : toTelefonoBOList(dto.getPhones());

        return UsuarioBO.builder()
                .name(name)
                .email(email)
                .password(password)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(Boolean.TRUE)
                .phones(phones)
                .build();
    }





    // Método para convertir una lista de TelefonoDTO a una lista de telefonoBO
    private static List<TelefonoBO> toTelefonoBOList(List<TelefonoDTO> phones) {
        if (Objects.isNull(phones)) {
            return null;
        }
        return phones.stream()
                .map(UsuarioMapper::toTelefonoBO) // mapear cada Telefono a TelefonoDTO
                .collect(Collectors.toList());
    }

    // Método para convertir una lista de TelefonoDTO a una lista de TelefonoBO
    private static List<TelefonoDTO> toTelefonoDTOList(List<TelefonoBO> phonesBO) {
        if (Objects.isNull(phonesBO)) {
            return null;
        }

        return phonesBO.stream()
                .map(UsuarioMapper::toTelefonoDTO) // mapear cada TelefonoDTO a Telefono
                .collect(Collectors.toList());
    }

    // Métodos para mapear un TelefonoDTO a un telefonoBO
    private static TelefonoBO toTelefonoBO(TelefonoDTO phone) {
        if (phone == null) {
            return null;
        }
        TelefonoBO bo = new TelefonoBO();
        bo.setNumber(phone.getNumber());
        bo.setCitycode(phone.getCitycode());
        bo.setContrycode(phone.getContrycode());
        return bo;
    }

    // Métodos para mapear un TelefonoBO a un telefonoDTO
    private static TelefonoDTO toTelefonoDTO(TelefonoBO bo) {
        if (Objects.isNull(bo)) {
            return null;
        }
        TelefonoDTO phone = new TelefonoDTO();
        phone.setNumber(bo.getNumber());
        phone.setCitycode(bo.getCitycode());
        phone.setContrycode(bo.getContrycode());
        return phone;
    }
}
