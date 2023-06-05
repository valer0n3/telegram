package ru.training;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class WebClientController {
    private final WebClient webClient;

    public WebClientController(@Value("${stats-server.url}") String statServerUrl) {
        this.webClient = WebClient.create(statServerUrl);
    }

    public List<DtoMuscleGet> getMuscle(String muscle) {
        List<String> getStatDtoList = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/muscle")
                        .build())
                .retrieve()
                .bodyToFlux(DtoMuscleGet.class)
                .collectList()
                .block();
        return null;
    }
}
