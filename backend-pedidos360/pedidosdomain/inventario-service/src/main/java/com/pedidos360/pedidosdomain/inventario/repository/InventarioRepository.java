package com.pedidos360.pedidosdomain.inventario.repository;

import com.pedidos360.pedidosdomain.inventario.model.Inventario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    Optional<Inventario> findByProductoId(Long productoId);
}