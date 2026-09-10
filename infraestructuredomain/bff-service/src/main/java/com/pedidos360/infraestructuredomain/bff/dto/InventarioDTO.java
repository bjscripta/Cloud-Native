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
public class InventarioDTO {

    private Long id;
    private Long productoId;
    private int cantidadDisponible;
    private int stockMinimo;
}