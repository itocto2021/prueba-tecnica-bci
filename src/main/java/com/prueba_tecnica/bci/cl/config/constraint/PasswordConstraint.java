package com.prueba_tecnica.bci.cl.config.constraint;

import com.prueba_tecnica.bci.cl.config.utils.Constantes;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación de restricción personalizada para validar contraseñas.
 *
 * <p>
 * Esta anotación se utiliza para aplicar la validación de contraseñas
 * en campos específicos de las clases de DTO. Requiere que las contraseñas
 * cumplan con los criterios definidos en el validador asociado.
 * </p>
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordConstraint {

    /**
     * Mensaje de error que se muestra cuando la validación falla.
     *
     * @return El mensaje de error por defecto.
     */
    String message() default Constantes.MSG_INVALID_PASSWORD;

    /**
     * Grupos de validación a los que esta restricción pertenece.
     *
     * @return Los grupos de validación.
     */
    Class<?>[] groups() default {};

    /**
     * Carga útil adicional que se puede utilizar para el procesamiento
     * de validación.
     *
     * @return La carga útil.
     */
    Class<? extends Payload>[] payload() default {};
}

