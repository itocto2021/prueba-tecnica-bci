package com.prueba_tecnica.bci.cl.domain.dto;

import com.prueba_tecnica.bci.cl.config.constraint.PasswordConstraint;
import com.prueba_tecnica.bci.cl.config.utils.Constantes;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Esta clase representa un DTO (Data Transfer Object) para un usuario.
 * Contiene los datos del nombre, correo electrónico, contraseña y una lista de teléfonos asociados.
 * <p>
 * La clase utiliza validaciones:
 * <ul>
 *   <li>@Pattern: Valida que el correo electrónico cumpla con un formato válido.</li>
 *   <li>@PasswordConstraint: Valida que la contraseña cumpla con las restricciones definidas por la anotación personalizada.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     UsuarioDTO usuarioDTO = new UsuarioDTO("Juan Perez", "juan.perez@example.com", "StrongPassword123", phones);
 * </pre>
 * </p>
 *
 * <p>
 * Ejemplo de teléfonos:
 * <pre>
 *     List<TelefonoDTO> phones = Arrays.asList(new TelefonoDTO("123456789", "01", "51"));
 *     usuarioDTO.setPhones(phones);
 * </pre>
 * </p>
 *
 * @author itocto
 * @version 1.0
 * @since 25/09/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private String id;
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = Constantes.MSG_INVALID_EMAIL)
    private String email;
    @PasswordConstraint
    private String password;
    private List<TelefonoDTO> phones;

}
