package com.prueba_tecnica.bci.cl.config.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

/**
 * Validador personalizado para la contraseña.
 *
 * <p>
 * Este validador utiliza una expresión regular definida en la configuración
 * para comprobar si la contraseña proporcionada cumple con los requisitos.
 * </p>
 */
public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    @Value("${password.regex}")
    private String passwordRegex;

    /**
     * Inicializa el validador.
     *
     * @param constraintAnnotation La anotación de restricción que se está validando.
     */
    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
    }

    /**
     * Valida la contraseña según la expresión regular proporcionada.
     *
     * @param password La contraseña a validar.
     * @param context  El contexto de validación que puede usarse para establecer mensajes de error.
     * @return true si la contraseña es válida, false en caso contrario.
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }
        return password.matches(passwordRegex);
    }
}

