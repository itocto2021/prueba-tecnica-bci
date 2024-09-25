package com.prueba_tecnica.bci.cl.config.security;

import com.prueba_tecnica.bci.cl.domain.dto.AuthResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

/**
 * Clase de utilidad para la generación de tokens JWT.
 * <p>
 * Esta clase se encarga de crear tokens de acceso para los usuarios
 * mediante la firma de los mismos con una clave secreta. Los tokens
 * generados tienen un tiempo de expiración predefinido.
 * </p>
 *
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     AuthResponse tokenResponse = JwtUtil.generateToken("usuarioEjemplo");
 * </pre>
 * </p>
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
public class JwtUtil {

    private static final String SECRET_KEY = "b614368ad5fedb18a81b194800fdbc03924e468210be8a4e92c5fe9ff3b52897"; // La misma clave usada en JwtFilter
    private static final Key SIGNING_KEY = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY), SignatureAlgorithm.HS256.getJcaName());


    /**
     * Genera un token JWT para un usuario dado.
     *
     * @param username El nombre de usuario para el que se generará el token.
     * @return Un objeto {@link AuthResponse} que contiene el token, el tipo de token y el tiempo de expiración.
     */
    public static AuthResponse generateToken(String username) {
        // Define el tiempo de expiración
        long expirationTimeMillis = 1000 * 60 * 60;
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTimeMillis);

        // Genera el token JWT
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SIGNING_KEY)
                .compact();

        long expiresIn = expirationTimeMillis / 1000;
        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .expiresIn(expiresIn)
                .build();
    }
}


