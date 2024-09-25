package com.prueba_tecnica.bci.cl.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Esta clase representa el objeto de negocio (BO) para un usuario.
 *
 * <p>
 *
 * Ejemplo de uso:
 * <pre>
 *     UsuarioBO usuarioBO = new UsuarioBO();
 *     usuarioBO.setName("Juan Perez");
 *     usuarioBO.setEmail("juan.perez@example.com");
 *     usuarioBO.setPassword("StrongPassword123");
 *     usuarioBO.setCreated(LocalDateTime.now());
 *     usuarioBO.setIsActive(true);
 * </pre>
 * </p>
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UsuarioBO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String email;
    private String password;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TelefonoBO> phones;

}
