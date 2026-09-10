package com.pedidos360.infraestructuredomain.bff.client;

import com.pedidos360.infraestructuredomain.bff.dto.InventarioDTO;
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
public class InventarioClient {

    private final WebClient webClient;
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public InventarioClient(WebClient.Builder builder) {
        this.webClient = builder.clone()
                .baseUrl("http://inventario-service")
                .defaultStatusHandler(
                        HttpStatusCode::isError,
                        response -> Mono.just(new ResponseStatusException(
                                response.statusCode(),
                                "Error del microservicio de inventario")))
                .build();
    }

    public List<InventarioDTO> getInventarios() {
        return webClient.get()
                .uri("/inventarios")
                .retrieve()
                .bodyToFlux(InventarioDTO.class)
                .collectList()
                .block(TIMEOUT);
    }

    public InventarioDTO getInventarioById(Long id) {
        return webClient.get()
                .uri("/inventarios/{id}", id)
                .retrieve()
                .bodyToMono(InventarioDTO.class)
                .block(TIMEOUT);
    }

    public InventarioDTO getInventarioByProductoId(Long productoId) {
        return webClient.get()
                .uri("/inventarios/producto/{productoId}", productoId)
                .retrieve()
                .bodyToMono(InventarioDTO.class)
                .block(TIMEOUT);
    }

    public InventarioDTO saveInventario(InventarioDTO inventario) {
        return webClient.post()
                .uri("/inventarios")
                .bodyValue(inventario)
                .retrieve()
                .bodyToMono(InventarioDTO.class)
                .block(TIMEOUT);
    }

    public InventarioDTO updateInventario(
            Long id, InventarioDTO inventario) {

        return webClient.put()
                .uri("/inventarios/{id}", id)
                .bodyValue(inventario)
                .retrieve()
                .bodyToMono(InventarioDTO.class)
                .block(TIMEOUT);
    }

    public String deleteInventario(Long id) {
        return webClient.delete()
                .uri("/inventarios/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .block(TIMEOUT);
    }
}