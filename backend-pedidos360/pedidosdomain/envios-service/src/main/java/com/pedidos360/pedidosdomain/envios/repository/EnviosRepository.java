package com.pedidos360.pedidosdomain.envios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedidos360.pedidosdomain.envios.model.Envios;

public interface EnviosRepository extends JpaRepository<Envios, Long> {

    Optional<Envios> findByPedidoId(Long pedidoId);
}