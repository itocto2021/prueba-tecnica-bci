package com.prueba_tecnica.bci.cl.service.impl;

import com.prueba_tecnica.bci.cl.service.generic.IAuthServices;
import org.springframework.stereotype.Service;

/**
 * Implementación de los servicios de autenticación definidos en la interfaz {@link IAuthServices}.
 * Esta clase proporciona la lógica para autenticar a los usuarios mediante su nombre de usuario
 * y contraseña.
 * <p>
 * Actualmente, la autenticación se realiza de forma estática, comparando el nombre de usuario
 * y la contraseña con valores predefinidos.
 * </p>
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
@Service
public class AuthServicesImpl implements IAuthServices {

    /**
     * Autentica a un usuario basado en su nombre de usuario y contraseña.
     *
     * @param username El nombre de usuario del usuario.
     * @param password La contraseña del usuario.
     * @return {@code true} si las credenciales son correctas, {@code false} en caso contrario.
     */
    @Override
    public boolean authenticate(String username, String password) {

        return "T50735".equals(username) && "Ild234Toc486#".equals(password);
    }
}
