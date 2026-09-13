package com.pedidos360.pedidosdomain.pedidos.controller;

import com.pedidos360.pedidosdomain.pedidos.model.Pedido;
import com.pedidos360.pedidosdomain.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.getPedidos();
    }

    @GetMapping("/{id}")
    public Pedido buscarPedido(@PathVariable("id") int id) {
        return pedidoService.getPedidoById(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Pedido> buscarPorCliente(
            @PathVariable("clienteId") Long clienteId) {

        return pedidoService.getPedidosByClienteId(clienteId);
    }

    @PostMapping
    public Pedido agregarPedido(@Valid @RequestBody Pedido pedido) {
        pedido.setId(0);
        pedido.setFechaCreacion(null);
        pedido.setEstado(Pedido.Estado.PENDIENTE);

        return pedidoService.savePedido(pedido);
    }

    @PutMapping("/{id}")
    public Pedido actualizarPedido(
            @PathVariable("id") int id,
            @Valid @RequestBody Pedido pedido) {

        Pedido existente = pedidoService.getPedidoById(id);

        existente.setClienteId(pedido.getClienteId());
        existente.setEstado(pedido.getEstado());
        existente.setDireccionEntrega(pedido.getDireccionEntrega());

        return pedidoService.savePedido(existente);
    }

    @DeleteMapping("/{id}")
    public String eliminarPedido(@PathVariable("id") int id) {
        return pedidoService.deletePedido(id);
    }
}