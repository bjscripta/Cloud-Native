package com.pedidos360.pedidosdomain.clientes.repository;

import com.pedidos360.pedidosdomain.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}