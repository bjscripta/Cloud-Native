package com.pedidos360.infraestructuredomain.bff.controller;

import com.pedidos360.infraestructuredomain.bff.client.PedidoClient;
import com.pedidos360.infraestructuredomain.bff.dto.PedidoDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author bjcan
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidoBffController {

    @Autowired
    private PedidoClient pedidoClient;

    @GetMapping
    public List<PedidoDTO> listarPedidos() {
        return pedidoClient.getPedidos();
    }

    @GetMapping("/{id}")
    public PedidoDTO buscarPedido(@PathVariable("id") int id) {
        return pedidoClient.getPedidoById(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<PedidoDTO> buscarPorCliente(
            @PathVariable("clienteId") Long clienteId) {

        return pedidoClient.getPedidosByClienteId(clienteId);
    }

    @PostMapping
    public PedidoDTO agregarPedido(@RequestBody PedidoDTO pedido) {
        return pedidoClient.savePedido(pedido);
    }

    @PutMapping("/{id}")
    public PedidoDTO actualizarPedido(
            @PathVariable("id") int id,
            @RequestBody PedidoDTO pedido) {

        return pedidoClient.updatePedido(id, pedido);
    }

    @DeleteMapping("/{id}")
    public String eliminarPedido(@PathVariable("id") int id) {
        return pedidoClient.deletePedido(id);
    }
}
