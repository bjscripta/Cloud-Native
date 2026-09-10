package com.pedidos360.infraestructuredomain.bff.controller;

import com.pedidos360.infraestructuredomain.bff.client.ClienteClient;
import com.pedidos360.infraestructuredomain.bff.dto.ClienteDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author bjcan
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteBffController {

    @Autowired
    private ClienteClient clienteClient;

    @GetMapping
    public List<ClienteDTO> listarClientes() {
        return clienteClient.getClientes();
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarCliente(@PathVariable("id") Long id) {
        return clienteClient.getClienteById(id);
    }

    @PostMapping
    public ClienteDTO agregarCliente(@RequestBody ClienteDTO cliente) {
        return clienteClient.saveCliente(cliente);
    }

    @PutMapping("/{id}")
    public ClienteDTO actualizarCliente(
            @PathVariable("id") Long id,
            @RequestBody ClienteDTO cliente) {

        return clienteClient.updateCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    public String eliminarCliente(@PathVariable("id") Long id) {
        return clienteClient.deleteCliente(id);
    }
}