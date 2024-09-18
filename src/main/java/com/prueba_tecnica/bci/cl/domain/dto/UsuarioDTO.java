package com.prueba_tecnica.bci.cl.domain.dto;

import com.prueba_tecnica.bci.cl.config.constraint.PasswordConstraint;
import com.prueba_tecnica.bci.cl.config.utils.Constantes;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = Constantes.MSG_INVALID_EMAIL)
    private String email;
    @PasswordConstraint
    private String password;
    private List<TelefonoDTO> phones;

}
