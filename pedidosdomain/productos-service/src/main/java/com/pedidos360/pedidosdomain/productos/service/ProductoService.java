package com.pedidos360.pedidosdomain.productos.service;

import com.pedidos360.pedidosdomain.productos.model.Producto;
import com.pedidos360.pedidosdomain.productos.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public String deleteProducto(Long id) {
        Producto producto = getProductoById(id);
        productoRepository.delete(producto);
        return "Producto eliminado";
    }
}