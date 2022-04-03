package org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ACSRepository {
    private String baseURL = "https://api.census.gov/data/2019/acs/acs1?get=NAME,B15011_024E,B15011_005E&for=state:*";
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

        List<List<String>> list = Arrays.stream(block)
                .map(Arrays::asList)
                .collect(Collectors.toList());
        list.remove(0);
        String[][] dataset = list.stream()
                .map(s -> s.stream().toArray(String[]::new))
                .toArray(String[][]::new);
        return dataset;
    }
}
