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

/**
 * Clase responsable de realizar la conversión entre los objetos de transferencia de datos (DTO)
 * y los objetos de negocio (BO) relacionados con los usuarios.
 * <p>
 * Esta clase implementa la interfaz {@link IGenericMapper} para proporcionar métodos de mapeo
 * específicos entre {@link UsuarioDTO} y {@link UsuarioBO}.
 * </p>
 *
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
@Component
public class UsuarioMapper implements IGenericMapper<UsuarioDTO, UsuarioBO> {

    /**
     * Convierte un objeto {@link UsuarioBO} a un objeto {@link UsuarioDTO}.
     *
     * @param bo El objeto de negocio a convertir.
     * @return El objeto de transferencia de datos correspondiente, o null si el objeto de negocio es null.
     */
    @Override
    public UsuarioDTO toDTO(UsuarioBO bo) {
        if (Objects.isNull(bo)) {
            return null;
        }

        String id = Objects.isNull(bo.getId()) ? null : String.valueOf(bo.getId());
        String name = Objects.isNull(bo.getName()) ? null : bo.getName();
        String email = Objects.isNull(bo.getEmail()) ? null : bo.getEmail();
        String password = Objects.isNull(bo.getPassword()) ? null : bo.getPassword();
        List<TelefonoDTO> phones = Objects.isNull(bo.getPhones()) ? null : toTelefonoDTOList(bo.getPhones());

        return UsuarioDTO.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .phones(phones)
                .build();
    }

    /**
     * Convierte un objeto {@link UsuarioDTO} a un objeto {@link UsuarioBO}.
     *
     * @param dto El objeto de transferencia de datos a convertir.
     * @return El objeto de negocio correspondiente.
     */
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
                .isActive(Boolean.TRUE)
                .phones(phones)
                .build();
    }





    /**
     * Convierte una lista de objetos {@link TelefonoDTO} a una lista de objetos {@link TelefonoBO}.
     *
     * @param phones La lista de objetos de transferencia de datos a convertir.
     * @return La lista de objetos de negocio correspondientes, o null si la lista es null.
     */
    private static List<TelefonoBO> toTelefonoBOList(List<TelefonoDTO> phones) {
        if (Objects.isNull(phones)) {
            return null;
        }
        return phones.stream()
                .map(UsuarioMapper::toTelefonoBO) // mapear cada Telefono a TelefonoDTO
                .collect(Collectors.toList());
    }

    /**
     * Convierte una lista de objetos {@link TelefonoBO} a una lista de objetos {@link TelefonoDTO}.
     *
     * @param phonesBO La lista de objetos de negocio a convertir.
     * @return La lista de objetos de transferencia de datos correspondientes, o null si la lista es null.
     */
    private static List<TelefonoDTO> toTelefonoDTOList(List<TelefonoBO> phonesBO) {
        if (Objects.isNull(phonesBO)) {
            return null;
        }

        return phonesBO.stream()
                .map(UsuarioMapper::toTelefonoDTO) // mapear cada TelefonoDTO a Telefono
                .collect(Collectors.toList());
    }

    /**
     * Convierte un objeto {@link TelefonoDTO} a un objeto {@link TelefonoBO}.
     *
     * @param phone El objeto de transferencia de datos a convertir.
     * @return El objeto de negocio correspondiente, o null si el objeto es null.
     */
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

    /**
     * Convierte un objeto {@link TelefonoBO} a un objeto {@link TelefonoDTO}.
     *
     * @param bo El objeto de negocio a convertir.
     * @return El objeto de transferencia de datos correspondiente, o null si el objeto de negocio es null.
     */
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
