package org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

public class ACSRepository {
    private String baseURL = "https://api.census.gov/data/2019/acs/acs1/groups/B15012/";
    private final WebClient webClient;

    public ACSRepository() {
        webClient = WebClient
                .builder()
                .baseUrl(baseURL)
                .build();
    }

    public String getResults(String query){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("", "")
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
