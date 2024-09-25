package com.prueba_tecnica.bci.cl.repository;

import com.prueba_tecnica.bci.cl.domain.entity.UsuarioBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repositorio para la gestión de usuarios en la base de datos.
 * Extiende {@link JpaRepository} para proporcionar operaciones CRUD
 * sobre los objetos {@link UsuarioBO}.
 *
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
public interface UsuarioRepository extends JpaRepository<UsuarioBO, UUID> {

    /**
     * Verifica si existe un usuario en la base de datos con el correo electrónico proporcionado.
     *
     * @param email El correo electrónico a verificar.
     * @return {@code true} si existe al menos un usuario con el correo electrónico dado,
     *         {@code false} en caso contrario.
     */
    boolean existsByEmail(String email);
}
