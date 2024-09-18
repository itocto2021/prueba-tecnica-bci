package com.prueba_tecnica.bci.cl.config.security;

import com.prueba_tecnica.bci.cl.domain.dto.AuthResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {

    //private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final String SECRET_KEY = "b614368ad5fedb18a81b194800fdbc03924e468210be8a4e92c5fe9ff3b52897"; // La misma clave usada en JwtFilter
    private static final Key SIGNING_KEY = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY), SignatureAlgorithm.HS256.getJcaName());


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


