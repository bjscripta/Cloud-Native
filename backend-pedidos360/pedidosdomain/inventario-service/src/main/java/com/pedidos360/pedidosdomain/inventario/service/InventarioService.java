package com.pedidos360.pedidosdomain.inventario.service;

import com.pedidos360.pedidosdomain.inventario.model.Inventario;
import com.pedidos360.pedidosdomain.inventario.repository.InventarioRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> getInventarios() {
        return inventarioRepository.findAll();
    }

    public Inventario getInventarioById(Long id) {
        return inventarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Inventario no encontrado"));
    }

    public Inventario getInventarioByProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Inventario del producto no encontrado"));
    }

    public Inventario saveInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public String deleteInventario(Long id) {
        Inventario inventario = getInventarioById(id);
        inventarioRepository.delete(inventario);
        return "Inventario eliminado";
    }
}