package org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository;

import org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository.dto.ACSResponse;
import org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository.dto.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ACSRepositoryTest {

    private ACSRepository acsRepository;

    @Mock
    WebClient webClientMock;

    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    WebClient.ResponseSpec responseSpecMock;

    @Mock
    Mono<ACSResponse> acsResponseMonoMock;

    @Mock
    Mono<String[][]> responseMonoMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        acsRepository = new ACSRepository(webClientMock);
    }

    @Test
    void whenGetResults_thenReturnACSResponse() {
            //given
            String query = "WA";
            Result expectedResult = new Result("WA","10","10");
            String expectedResults = expectedResult.toString();
            String[][] dataset = {{"CA", "10", "10"}, {"WA", "10", "10"}, {"OR", "10", "10"}};

            when(webClientMock.get())
                    .thenReturn(requestHeadersUriSpecMock);
            when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any()))
                    .thenReturn(requestHeadersSpecMock);
            when(requestHeadersSpecMock.retrieve())
                    .thenReturn(responseSpecMock);
            when(responseSpecMock.bodyToMono(String[][].class)) // something here is wrong
                    .thenReturn(responseMonoMock);
            when(responseMonoMock.block())
                    .thenReturn(dataset);

            //when
            String actualACSResults = acsRepository.getResults(query).toString();

            //then
            assertEquals(expectedResults, actualACSResults);
    }

}