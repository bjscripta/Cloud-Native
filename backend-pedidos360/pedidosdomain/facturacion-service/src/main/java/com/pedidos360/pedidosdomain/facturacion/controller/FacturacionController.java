package com.pedidos360.pedidosdomain.facturacion.controller;

import com.pedidos360.pedidosdomain.facturacion.model.Facturacion;
import com.pedidos360.pedidosdomain.facturacion.service.FacturacionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facturas")
public class FacturacionController {

    @Autowired
    private FacturacionService facturacionService;

    @GetMapping
    public List<Facturacion> listarFacturaciones() {
        return facturacionService.getFacturaciones();
    }

    @GetMapping("/{id}")
    public Facturacion buscarFacturacion(@PathVariable("id") Long id) {
        return facturacionService.getFacturacionById(id);
    }

    @GetMapping("/pedido/{pedidoId}")
    public Facturacion buscarPorPedido(@PathVariable("pedidoId") Long pedidoId) {
        return facturacionService.getFacturaciones().stream()
                .filter(facturacion -> facturacion.getPedidoId() != null
                        && facturacion.getPedidoId().equals(pedidoId))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Facturacion agregarFacturacion(@Valid @RequestBody Facturacion facturacion) {
        facturacion.setId(null);
        return facturacionService.saveFacturacion(facturacion);
    }

    @PutMapping("/{id}")
    public Facturacion actualizarFacturacion(
            @PathVariable("id") Long id,
            @Valid @RequestBody Facturacion facturacion) {

        Facturacion existente = facturacionService.getFacturacionById(id);

        existente.setPedidoId(facturacion.getPedidoId());
        existente.setClienteId(facturacion.getClienteId());
        existente.setMontoTotal(facturacion.getMontoTotal());
        existente.setEstadoPago(facturacion.getEstadoPago());

        return facturacionService.saveFacturacion(existente);
    }

    @DeleteMapping("/{id}")
    public String eliminarFacturacion(@PathVariable("id") Long id) {
        return facturacionService.deleteFacturacion(id);
    }
}