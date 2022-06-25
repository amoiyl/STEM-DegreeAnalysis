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
        String blockString = webClient.get()
                .uri(uriBuilder -> uriBuilder.build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        String[] blockArr1 = blockString.replace("[", "").split("],");
//        System.err.println(Arrays.deepToString(blockArr1));
        String[][] blockArr2 = new String[blockArr1.length][4];

        for(int state = 0; state < blockArr1.length; state++) {
            String[] transitionArr = blockArr1[state].split(",");
            for(int value = 0; value < 4; value++) {
                blockArr2[state][value] = transitionArr[value];
            }
        }

        //System.err.println(blockArr2.getClass().equals(String[][].class));
//        System.err.println(Arrays.deepToString(blockArr2));

        // remove first element
        List<List<String>> list = Arrays.stream(blockArr2)
                .map(Arrays::asList)
                .collect(Collectors.toList());
        list.remove(0);
        String[][] dataset = list.stream()
                .map(s -> s.stream().toArray(String[]::new))
                .toArray(String[][]::new);

        String[] requestedData;
        try {
            requestedData = Arrays.stream(dataset).filter(datum -> datum[0].contains(query)).collect(Collectors.toList()).get(0);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result(s) not found.");
        }
        System.err.println(requestedData[0]);
//        System.err.println(Arrays.deepToString(requestedData));

       // split into Result

//        Result result = new Result(requestedData[0], requestedData[1], requestedData[2]);
//        System.err.println(requestedData[0] + "NOBO");

        return new Result(requestedData[0], requestedData[1], requestedData[2]);
    }
}
