package com.pedidos360.pedidosdomain.productos.controller;

import com.pedidos360.pedidosdomain.productos.model.Producto;
import com.pedidos360.pedidosdomain.productos.service.ProductoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.getProductos();
    }

    @GetMapping("/{id}")
    public Producto buscarProducto(@PathVariable("id") Long id) {
        return productoService.getProductoById(id);
    }

    @PostMapping
    public Producto agregarProducto(@Valid @RequestBody Producto producto) {
        producto.setId(null);
        return productoService.saveProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(
            @PathVariable("id") Long id,
            @Valid @RequestBody Producto producto) {

        Producto existente = productoService.getProductoById(id);

        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        existente.setActivo(producto.getActivo());

        return productoService.saveProducto(existente);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        return productoService.deleteProducto(id);
    }
}