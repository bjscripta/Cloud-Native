package com.pedidos360.infraestructuredomain.bff.client;

import com.pedidos360.infraestructuredomain.bff.dto.PedidoDTO;
import java.time.Duration;
import java.util.List;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
/**
 *
 * @author bjcan
 */
@Component
public class PedidoClient {

    private final WebClient webClient;
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public PedidoClient(WebClient.Builder builder) {
        this.webClient = builder.clone()
                .baseUrl("http://pedidos-service")
                .defaultStatusHandler(
                        HttpStatusCode::isError,
                        response -> Mono.just(new ResponseStatusException(
                                response.statusCode(),
                                "Error del microservicio de pedidos")))
                .build();
    }

    public List<PedidoDTO> getPedidos() {
        return webClient.get()
                .uri("/pedidos")
                .retrieve()
                .bodyToFlux(PedidoDTO.class)
                .collectList()
                .block(TIMEOUT);
    }

    public PedidoDTO getPedidoById(int id) {
        return webClient.get()
                .uri("/pedidos/{id}", id)
                .retrieve()
                .bodyToMono(PedidoDTO.class)
                .block(TIMEOUT);
    }

    public List<PedidoDTO> getPedidosByClienteId(Long clienteId) {
        return webClient.get()
                .uri("/pedidos/cliente/{clienteId}", clienteId)
                .retrieve()
                .bodyToFlux(PedidoDTO.class)
                .collectList()
                .block(TIMEOUT);
    }

    public PedidoDTO savePedido(PedidoDTO pedido) {
        return webClient.post()
                .uri("/pedidos")
                .bodyValue(pedido)
                .retrieve()
                .bodyToMono(PedidoDTO.class)
                .block(TIMEOUT);
    }

    public PedidoDTO updatePedido(int id, PedidoDTO pedido) {
        return webClient.put()
                .uri("/pedidos/{id}", id)
                .bodyValue(pedido)
                .retrieve()
                .bodyToMono(PedidoDTO.class)
                .block(TIMEOUT);
    }

    public String deletePedido(int id) {
        return webClient.delete()
                .uri("/pedidos/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .block(TIMEOUT);
    }
}