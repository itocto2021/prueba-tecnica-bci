package com.prueba_tecnica.bci.cl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefonoDTO {
    private String number;
    private String citycode;
    private String contrycode;
}
