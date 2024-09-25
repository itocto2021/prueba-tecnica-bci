package com.prueba_tecnica.bci.cl.service.generic;

/**
 * Esta interfaz define los servicios de autenticación para la aplicación.
 * Proporciona los métodos necesarios para autenticar usuarios mediante su
 * nombre de usuario y contraseña.
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
public interface IAuthServices {

    boolean authenticate(String username, String password);
}
