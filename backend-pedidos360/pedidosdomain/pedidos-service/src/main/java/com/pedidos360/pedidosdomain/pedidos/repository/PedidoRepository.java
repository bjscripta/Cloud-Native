package com.pedidos360.pedidosdomain.pedidos.repository;

import com.pedidos360.pedidosdomain.pedidos.model.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByClienteId(Long clienteId);
}