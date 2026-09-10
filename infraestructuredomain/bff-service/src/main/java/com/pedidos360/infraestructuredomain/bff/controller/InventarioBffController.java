package com.pedidos360.infraestructuredomain.bff.controller;

import com.pedidos360.infraestructuredomain.bff.client.InventarioClient;
import com.pedidos360.infraestructuredomain.bff.dto.InventarioDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author bjcan
 */
@RestController
@RequestMapping("/api/inventarios")
public class InventarioBffController {

    @Autowired
    private InventarioClient inventarioClient;

    @GetMapping
    public List<InventarioDTO> listarInventarios() {
        return inventarioClient.getInventarios();
    }

    @GetMapping("/{id}")
    public InventarioDTO buscarInventario(@PathVariable("id") Long id) {
        return inventarioClient.getInventarioById(id);
    }

    @GetMapping("/producto/{productoId}")
    public InventarioDTO buscarPorProducto(
            @PathVariable("productoId") Long productoId) {

        return inventarioClient.getInventarioByProductoId(productoId);
    }

    @PostMapping
    public InventarioDTO agregarInventario(
            @RequestBody InventarioDTO inventario) {

        return inventarioClient.saveInventario(inventario);
    }

    @PutMapping("/{id}")
    public InventarioDTO actualizarInventario(
            @PathVariable("id") Long id,
            @RequestBody InventarioDTO inventario) {

        return inventarioClient.updateInventario(id, inventario);
    }

    @DeleteMapping("/{id}")
    public String eliminarInventario(@PathVariable("id") Long id) {
        return inventarioClient.deleteInventario(id);
    }
}