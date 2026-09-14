package com.pedidos360.pedidosdomain.envios.controller;

import com.pedidos360.pedidosdomain.envios.model.Envios;
import com.pedidos360.pedidosdomain.envios.service.EnviosService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/envios")
public class EnviosController {

    @Autowired
    private EnviosService enviosService;

    @GetMapping
    public List<Envios> listarEnvios() {
        return enviosService.getEnvios();
    }

    @GetMapping("/{id}")
    public Envios buscarEnvio(@PathVariable("id") Long id) {
        return enviosService.getEnvioById(id);
    }

    @GetMapping("/pedido/{pedidoId}")
    public Envios buscarPorPedido(@PathVariable("pedidoId") Long pedidoId) {
        return enviosService.getEnvioByPedidoId(pedidoId);
    }

    @PostMapping
    public Envios agregarEnvio(@Valid @RequestBody Envios envio) {
        envio.setId(null);
        return enviosService.saveEnvio(envio);
    }

    @PutMapping("/{id}")
    public Envios actualizarEnvio(
            @PathVariable("id") Long id,
            @Valid @RequestBody Envios envio) {

        Envios existente = enviosService.getEnvioById(id);

        existente.setPedidoId(envio.getPedidoId());
        existente.setDireccionEntrega(envio.getDireccionEntrega());
        existente.setEstadoEnvio(envio.getEstadoEnvio());
        existente.setNumeroSeguimiento(envio.getNumeroSeguimiento());

        return enviosService.saveEnvio(existente);
    }

    @DeleteMapping("/{id}")
    public String eliminarEnvio(@PathVariable("id") Long id) {
        return enviosService.deleteEnvio(id);
    }
}