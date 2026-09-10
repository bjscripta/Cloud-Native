package com.pedidos360.infraestructuredomain.bff.client;

import com.pedidos360.infraestructuredomain.bff.dto.ClienteDTO;
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
public class ClienteClient {

    private final WebClient webClient;
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public ClienteClient(WebClient.Builder builder) {
        this.webClient = builder.clone()
                .baseUrl("http://clientes-service")
                .defaultStatusHandler(
                        HttpStatusCode::isError,
                        response -> Mono.just(new ResponseStatusException(
                                response.statusCode(),
                                "Error del microservicio de clientes")))
                .build();
    }

    public List<ClienteDTO> getClientes() {
        return webClient.get()
                .uri("/clientes")
                .retrieve()
                .bodyToFlux(ClienteDTO.class)
                .collectList()
                .block(TIMEOUT);
    }

    public ClienteDTO getClienteById(Long id) {
        return webClient.get()
                .uri("/clientes/{id}", id)
                .retrieve()
                .bodyToMono(ClienteDTO.class)
                .block(TIMEOUT);
    }

    public ClienteDTO saveCliente(ClienteDTO cliente) {
        return webClient.post()
                .uri("/clientes")
                .bodyValue(cliente)
                .retrieve()
                .bodyToMono(ClienteDTO.class)
                .block(TIMEOUT);
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO cliente) {
        return webClient.put()
                .uri("/clientes/{id}", id)
                .bodyValue(cliente)
                .retrieve()
                .bodyToMono(ClienteDTO.class)
                .block(TIMEOUT);
    }

    public String deleteCliente(Long id) {
        return webClient.delete()
                .uri("/clientes/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .block(TIMEOUT);
    }
}