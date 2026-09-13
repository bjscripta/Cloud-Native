package com.pedidos360.infraestructuredomain.bff.client;

import com.pedidos360.infraestructuredomain.bff.dto.ProductoDTO;
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
public class ProductoClient {

    private final WebClient webClient;
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public ProductoClient(WebClient.Builder builder) {
        this.webClient = builder.clone()
                .baseUrl("http://productos-service")
                .defaultStatusHandler(
                        HttpStatusCode::isError,
                        response -> Mono.just(new ResponseStatusException(
                                response.statusCode(),
                                "Error del microservicio de productos")))
                .build();
    }

    public List<ProductoDTO> getProductos() {
        return webClient.get()
                .uri("/productos")
                .retrieve()
                .bodyToFlux(ProductoDTO.class)
                .collectList()
                .block(TIMEOUT);
    }

    public ProductoDTO getProductoById(Long id) {
        return webClient.get()
                .uri("/productos/{id}", id)
                .retrieve()
                .bodyToMono(ProductoDTO.class)
                .block(TIMEOUT);
    }

    public ProductoDTO saveProducto(ProductoDTO producto) {
        return webClient.post()
                .uri("/productos")
                .bodyValue(producto)
                .retrieve()
                .bodyToMono(ProductoDTO.class)
                .block(TIMEOUT);
    }

    public ProductoDTO updateProducto(Long id, ProductoDTO producto) {
        return webClient.put()
                .uri("/productos/{id}", id)
                .bodyValue(producto)
                .retrieve()
                .bodyToMono(ProductoDTO.class)
                .block(TIMEOUT);
    }

    public String deleteProducto(Long id) {
        return webClient.delete()
                .uri("/productos/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .block(TIMEOUT);
    }
}