package org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository.dto.Result;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

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

    public ACSRepository(WebClient webClientMock) {
        this.webClient = webClientMock;
    }

    public Result getResults(String query){
        String[][] block = webClient.get()
                .uri(uriBuilder -> uriBuilder.build())
                .retrieve()
                .bodyToMono(String[][].class)
                .block();

        // remove first element
        List<List<String>> list = Arrays.stream(block)
                .map(Arrays::asList)
                .collect(Collectors.toList());
        list.remove(0);
        String[][] dataset = list.stream()
                .map(s -> s.stream().toArray(String[]::new))
                .toArray(String[][]::new);

        // System.out.println(Arrays.deepToString(dataset));

        try {
            Arrays.stream(dataset).filter(datum -> datum[0].equals(query)).collect(Collectors.toList()).get(0);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result(s) not found.");
        }

        // get state data
        String[] requestedData = Arrays.stream(dataset).filter(datum -> datum[0].equals(query)).collect(Collectors.toList()).get(0);
        // split into Result
        return new Result(requestedData[0], requestedData[1], requestedData[2]);
    }
}
