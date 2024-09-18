package com.prueba_tecnica.bci.cl.repository;

import com.prueba_tecnica.bci.cl.domain.entity.UsuarioBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioBO, UUID> {
    boolean existsByEmail(String email);
}
