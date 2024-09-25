package com.prueba_tecnica.bci.cl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa un modelo de solicitud de autenticación.
 * Contiene los campos de nombre de usuario y contraseña necesarios para la autenticación.
 * <p>
 *
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     AuthRequest authRequest = new AuthRequest("usuario123", "contraseña123");
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
public class AuthRequest {
    private String username;
    private String password;
}
