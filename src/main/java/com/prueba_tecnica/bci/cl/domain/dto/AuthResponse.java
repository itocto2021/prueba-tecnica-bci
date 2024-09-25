package com.prueba_tecnica.bci.cl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa la respuesta de autenticación.
 * Contiene el token generado, el tipo de token y el tiempo de expiración.
 * <p>
 * Esta clase es útil para devolver la información necesaria después de una autenticación exitosa.
 * </p>
 *
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     AuthResponse authResponse = new AuthResponse("token123", "Bearer", jjsndahbhbdhbsdbh456564er15614d65e41r56);
 * </pre>
 * </p>
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private String tokenType;
    private Long expiresIn;
}
