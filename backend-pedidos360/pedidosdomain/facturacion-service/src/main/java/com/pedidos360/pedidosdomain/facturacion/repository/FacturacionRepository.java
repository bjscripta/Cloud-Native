package com.pedidos360.pedidosdomain.facturacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedidos360.pedidosdomain.facturacion.model.Facturacion;

public interface FacturacionRepository extends JpaRepository<Facturacion, Long> {

    Optional<Facturacion> findByPedidoId(Long pedidoId);
}