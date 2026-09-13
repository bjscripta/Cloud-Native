package com.pedidos360.pedidosdomain.inventario.controller;

import com.pedidos360.pedidosdomain.inventario.model.Inventario;
import com.pedidos360.pedidosdomain.inventario.service.InventarioService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<Inventario> listarInventarios() {
        return inventarioService.getInventarios();
    }

    @GetMapping("/{id}")
    public Inventario buscarInventario(@PathVariable("id") Long id) {
        return inventarioService.getInventarioById(id);
    }

    @GetMapping("/producto/{productoId}")
    public Inventario buscarPorProducto(
            @PathVariable("productoId") Long productoId) {

        return inventarioService.getInventarioByProductoId(productoId);
    }

    @PostMapping
    public Inventario agregarInventario(
            @Valid @RequestBody Inventario inventario) {

        inventario.setId(null);
        return inventarioService.saveInventario(inventario);
    }

    @PutMapping("/{id}")
    public Inventario actualizarInventario(
            @PathVariable("id") Long id,
            @Valid @RequestBody Inventario inventario) {

        Inventario existente = inventarioService.getInventarioById(id);

        existente.setProductoId(inventario.getProductoId());
        existente.setCantidadDisponible(inventario.getCantidadDisponible());
        existente.setStockMinimo(inventario.getStockMinimo());

        return inventarioService.saveInventario(existente);
    }

    @DeleteMapping("/{id}")
    public String eliminarInventario(@PathVariable("id") Long id) {
        return inventarioService.deleteInventario(id);
    }
}