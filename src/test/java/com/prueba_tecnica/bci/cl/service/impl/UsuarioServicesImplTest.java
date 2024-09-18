package com.prueba_tecnica.bci.cl.service.impl;

import com.prueba_tecnica.bci.cl.config.mapper.impl.UsuarioMapper;
import com.prueba_tecnica.bci.cl.config.mapper.impl.UsuarioResponseMapper;
import com.prueba_tecnica.bci.cl.config.utils.Constantes;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioDTO;
import com.prueba_tecnica.bci.cl.domain.dto.UsuarioResponse;
import com.prueba_tecnica.bci.cl.domain.entity.UsuarioBO;
import com.prueba_tecnica.bci.cl.exception.UsuarioException;
import com.prueba_tecnica.bci.cl.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServicesImplTest {

    public static final String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJUNTA3MzUiLCJpYXQiOjE3MjY2MTUwOTYsImV4cCI6MTcyNjYxODY5Nn0.NgpI2ZmOp1bjTQj3xQgPSUe7vSJjmOv2BWgTMFI21xhCrV1f5nWPk1-OkxxAsqK1";
    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private UsuarioResponseMapper responseMapper;

    @InjectMocks
    private UsuarioServicesImpl usuarioService;

    private UsuarioDTO usuarioDTO;
    private UsuarioBO usuarioBO;
    private UsuarioResponse usuarioResponse;

    @BeforeEach
    public void setUp() {
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("test@example.com");

        usuarioBO = new UsuarioBO();
        usuarioBO.setEmail("test@example.com");

    }

    @Test
    public void createUserSuccesTest() throws UsuarioException {

        when(usuarioRepository.existsByEmail(usuarioDTO.getEmail())).thenReturn(false);
        when(usuarioMapper.fromDTO(usuarioDTO)).thenReturn(usuarioBO);
        when(usuarioRepository.save(usuarioBO)).thenReturn(usuarioBO);
        when(responseMapper.toDTO(usuarioBO)).thenReturn(usuarioResponse);

        UsuarioResponse result = usuarioService.crearUsuario(usuarioDTO,token);

        assertNotNull(result);

        verify(usuarioRepository).existsByEmail(usuarioDTO.getEmail());
        verify(usuarioMapper).fromDTO(usuarioDTO);
        verify(usuarioRepository).save(usuarioBO);
        verify(responseMapper).toDTO(usuarioBO);
    }

    @Test
    public void createUserAlreadyExistsTest() {

        when(usuarioRepository.existsByEmail(usuarioDTO.getEmail())).thenReturn(true);

        UsuarioException thrown = assertThrows(UsuarioException.class, () -> usuarioService.crearUsuario(usuarioDTO,token));
        assertEquals(Constantes.MSG_EXIST_EMAIL, thrown.getMessage());

        verify(usuarioRepository).existsByEmail(usuarioDTO.getEmail());
        verifyNoMoreInteractions(usuarioMapper, usuarioRepository, responseMapper);
    }
}
