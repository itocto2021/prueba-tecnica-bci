package com.prueba_tecnica.bci.cl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa la respuesta de un usuario después de realizar una operación exitosa,
 * como la creación o actualización de un usuario en el sistema.
 * <p>
 * Contiene información clave como el ID del usuario, fechas de creación y modificación,
 * último inicio de sesión, token de autenticación y el estado de actividad.
 * </p>
 *
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     UsuarioResponse usuarioResponse = new UsuarioResponse();
 *     usuarioResponse.setId("12345");
 *     usuarioResponse.setCreated("2024-09-25T10:30:00");
 *     usuarioResponse.setModified("2024-09-25T10:40:00");
 *     usuarioResponse.setLastLogin("2024-09-25T10:45:00");
 *     usuarioResponse.setToken("abcd1234token");
 *     usuarioResponse.setIsActive("true");
 * </pre>
 * </p>

 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
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
