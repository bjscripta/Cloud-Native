package com.pedidos360.infraestructuredomain.bff.dto;

import java.time.LocalDateTime;
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
public class PedidoDTO {

    private int id;
    private Long clienteId;
    private LocalDateTime fechaCreacion;
    private Estado estado = Estado.PENDIENTE;
    private float total;
    private String direccionEntrega;

    public enum Estado {
        PENDIENTE,
        CONFIRMADO,
        ENVIADO,
        ENTREGADO,
        CANCELADO
    }
}