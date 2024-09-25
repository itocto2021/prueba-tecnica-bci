package com.prueba_tecnica.bci.cl.exception;

import java.io.Serializable;

/**
 * Excepción personalizada para manejar errores relacionados con el usuario.
 *
 * <p>
 * Esta excepción se lanza cuando hay problemas relacionados con la
 * gestión de usuarios, como intentos de registro con correos electrónicos
 * ya existentes o fallas en la validación de datos.
 * </p>
 */
public class UsuarioException extends Exception implements Serializable {

    static final long serialVersionUID = -3387516993124229948L;

    public UsuarioException() {
        super();
    }

    public UsuarioException(String message) {
        super(message);
    }

    public UsuarioException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsuarioException(Throwable cause) {
        super(cause);
    }

    protected UsuarioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
