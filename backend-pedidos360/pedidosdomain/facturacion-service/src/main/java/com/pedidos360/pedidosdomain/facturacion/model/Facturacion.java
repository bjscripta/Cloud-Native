package com.pedidos360.pedidosdomain.facturacion.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "facturas")
public class Facturacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pedidoId;

    @Column(nullable = false)
    private Long clienteId;

    @Column(nullable = false)
    private double montoTotal = 0.0;

    @Column(nullable = false)
    private String estadoPago = "PENDIENTE";
}