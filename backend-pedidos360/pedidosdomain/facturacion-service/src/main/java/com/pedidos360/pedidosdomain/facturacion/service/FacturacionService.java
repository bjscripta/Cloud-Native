package com.pedidos360.pedidosdomain.facturacion.service;

import com.pedidos360.pedidosdomain.facturacion.model.Facturacion;
import com.pedidos360.pedidosdomain.facturacion.repository.FacturacionRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class FacturacionService {

    @Autowired
    private FacturacionRepository facturacionRepository;

    public List<Facturacion> getFacturaciones() {
        return facturacionRepository.findAll();
    }

    public Facturacion getFacturacionById(Long id) {
        return facturacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Factura no encontrada"));
    }

    public Facturacion getFacturaByPedidoId(Long pedidoId) {
        return facturacionRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Factura del pedido no encontrada"));
    }

    public Facturacion saveFacturacion(Facturacion facturacion) {
        return facturacionRepository.save(facturacion);
    }

    public String deleteFacturacion(Long id) {
        Facturacion facturacion = getFacturacionById(id);
        facturacionRepository.delete(facturacion);
        return "Factura eliminada";
    }
}