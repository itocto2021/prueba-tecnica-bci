package com.prueba_tecnica.bci.cl.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Filtro para la validación de tokens JWT en las solicitudes HTTP.
 * Extiende {@link OncePerRequestFilter} para asegurar que el filtrado
 * se realice una vez por cada solicitud.
 * <p>
 * Este filtro verifica la existencia de un token JWT en el encabezado
 * de autorización y valida su firma. Si el token es válido, se extrae
 * el nombre de usuario y se crea una autenticación en el contexto de
 * seguridad de Spring.
 * </p>
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
public class JwtFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "b614368ad5fedb18a81b194800fdbc03924e468210be8a4e92c5fe9ff3b52897"; // La misma clave usada en JwtUtil
    private static final Key SIGNING_KEY = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY), SignatureAlgorithm.HS256.getJcaName());
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";


    /**
     * Método que realiza el filtrado de la solicitud para verificar el token JWT.
     *
     * @param request La solicitud HTTP entrante.
     * @param response La respuesta HTTP que se enviará al cliente.
     * @param filterChain La cadena de filtros que se debe ejecutar.
     * @throws ServletException Si ocurre un error durante el procesamiento.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(AUTH_HEADER);

        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            String token = authHeader.substring(BEARER_PREFIX.length());
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(SIGNING_KEY)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();


                String username = claims.getSubject();
                if (username != null) {
                    // Crea una autenticación falsa
                    User principal = new User(username, "", new ArrayList<>());
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // Manejo de excepciones para token inválido o expirado
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

