package org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class ACSRepository {
    private String baseURL = "http://api.census.gov/data/2019/acs/acs1?get=NAME,C15010C_003E,B15011_024E,B15011_005E&for=state:*";
    private final WebClient webClient;

    public ACSRepository() {
        webClient = WebClient
                .builder()
                .baseUrl(baseURL)
                .build();
    }

    public String[][] getResults(){
        String[][] block = webClient.get()
                .uri(uriBuilder -> uriBuilder.build())
                .retrieve()
                .bodyToMono(String[][].class)
                .block();
        System.err.println(block);
        return block;
    }
}
