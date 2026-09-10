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
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Float precio;
    private Boolean activo = true;
}