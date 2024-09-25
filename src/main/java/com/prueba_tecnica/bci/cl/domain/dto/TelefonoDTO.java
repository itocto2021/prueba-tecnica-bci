package com.prueba_tecnica.bci.cl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa un DTO (Data Transfer Object) para un teléfono.
 * Contiene el número de teléfono, el código de ciudad y el código de país.
 * <p>
 * Esta clase es útil para transferir la información de un teléfono entre diferentes partes del sistema.
 * </p>
 *
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     TelefonoDTO telefonoDTO = new TelefonoDTO("123456789", "01", "51");
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
public class TelefonoDTO {
    private String number;
    private String citycode;
    private String contrycode;
}
