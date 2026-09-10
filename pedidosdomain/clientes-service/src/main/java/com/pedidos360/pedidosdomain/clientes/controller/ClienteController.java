package com.pedidos360.pedidosdomain.clientes.controller;

import com.pedidos360.pedidosdomain.clientes.model.Cliente;
import com.pedidos360.pedidosdomain.clientes.service.ClienteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.getClientes();
    }

    @GetMapping("/{id}")
    public Cliente buscarCliente(@PathVariable("id") Long id) {
        return clienteService.getClienteById(id);
    }

    @PostMapping
    public Cliente agregarCliente(@Valid @RequestBody Cliente cliente) {
        cliente.setId(null);
        return clienteService.saveCliente(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(
            @PathVariable("id") Long id,
            @Valid @RequestBody Cliente cliente) {

        Cliente existente = clienteService.getClienteById(id);

        existente.setNombre(cliente.getNombre());
        existente.setEmail(cliente.getEmail());
        existente.setTelefono(cliente.getTelefono());
        existente.setDireccion(cliente.getDireccion());

        return clienteService.saveCliente(existente);
    }

    @DeleteMapping("/{id}")
    public String eliminarCliente(@PathVariable("id") Long id) {
        return clienteService.deleteCliente(id);
    }
}