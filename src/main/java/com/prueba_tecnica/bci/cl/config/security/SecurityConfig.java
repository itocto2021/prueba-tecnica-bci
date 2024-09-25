package com.prueba_tecnica.bci.cl.config.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase de configuración de seguridad para la aplicación.
 * <p>
 * Esta clase define las reglas de seguridad utilizando Spring Security,
 * incluyendo la configuración de rutas accesibles y la integración del filtro
 * JWT para la autenticación.
 * </p>
 *
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura la cadena de filtros de seguridad para la aplicación.
     *
     * @param http La instancia de {@link HttpSecurity} para configurar la seguridad.
     * @return La cadena de filtros de seguridad configurada.
     * @throws Exception Si ocurre un error al configurar la seguridad.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/authenticate").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())  // Desactiva la protección CSRF
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
