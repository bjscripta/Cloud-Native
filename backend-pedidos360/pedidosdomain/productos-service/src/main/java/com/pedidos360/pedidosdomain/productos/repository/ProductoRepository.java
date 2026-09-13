package com.pedidos360.pedidosdomain.productos.repository;

import com.pedidos360.pedidosdomain.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}