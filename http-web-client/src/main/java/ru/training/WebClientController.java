package ru.training;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class WebClientController {
    private final WebClient webClient;

    public WebClientController(@Value("${main-service.url}") String statServerUrl) {
        this.webClient = WebClient.create(statServerUrl);
    }

    public List<Object> getMuscle(String muscle) {
        List<Object> getStatDtoList = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/muscle")
                        .queryParam("muscleName", muscle)
                        .build())
                //.uri("${main-service.url}/muscle")
                .retrieve()
                .bodyToFlux(Object.class)
                .collectList()
                .block();
        return getStatDtoList;
    }

    public List<DtoMuscleGetAll> getAllMuscles() {
        List<DtoMuscleGetAll> dtoMuscleGet = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/muscle/all")
                        .build())
                //.uri("${main-service.url}/muscle")
                .retrieve()
                .bodyToFlux(DtoMuscleGetAll.class)
                .collectList()
                .block();
        return dtoMuscleGet;
    }
}
