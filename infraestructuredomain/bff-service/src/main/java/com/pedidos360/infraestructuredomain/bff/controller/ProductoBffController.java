package com.pedidos360.infraestructuredomain.bff.controller;

import com.pedidos360.infraestructuredomain.bff.client.ProductoClient;
import com.pedidos360.infraestructuredomain.bff.dto.ProductoDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author bjcan
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoBffController {

    @Autowired
    private ProductoClient productoClient;

    @GetMapping
    public List<ProductoDTO> listarProductos() {
        return productoClient.getProductos();
    }

    @GetMapping("/{id}")
    public ProductoDTO buscarProducto(@PathVariable("id") Long id) {
        return productoClient.getProductoById(id);
    }

    @PostMapping
    public ProductoDTO agregarProducto(@RequestBody ProductoDTO producto) {
        return productoClient.saveProducto(producto);
    }

    @PutMapping("/{id}")
    public ProductoDTO actualizarProducto(
            @PathVariable("id") Long id,
            @RequestBody ProductoDTO producto) {

        return productoClient.updateProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        return productoClient.deleteProducto(id);
    }
}