package com.prueba_tecnica.bci.cl.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa el objeto de negocio (BO) para un teléfono.
 * Contiene el ID del teléfono, el número de teléfono, el código de ciudad y el código de país.
 * <p>
 * La clase está mapeada para persistencia en una base de datos, con el campo {@code id} como clave primaria
 * generada automáticamente.
 * </p>
 *
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     TelefonoBO telefonoBO = new TelefonoBO();
 *     telefonoBO.setNumber("123456789");
 *     telefonoBO.setCitycode("01");
 *     telefonoBO.setContrycode("51");
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
@Entity
public class TelefonoBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String citycode;
    private String contrycode;

}
