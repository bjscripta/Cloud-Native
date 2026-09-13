package com.pedidos360.infraestructuredomain.bff.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author bjcan
 */
@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
}