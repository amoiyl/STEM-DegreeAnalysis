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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        acsRepository = new ACSRepository(webClientMock);
    }

    @Test
    void whenGetResults_thenReturnACSResponse() {
            //given
            String query = "CA";
            ACSResponse acsResponse = new ACSResponse();
            String[][] expectedResults = {{"CA", "1", "10"}};
            acsResponse.setResults(expectedResults);

            when(webClientMock.get())
                    .thenReturn(requestHeadersUriSpecMock);
            when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any()))
                    .thenReturn(requestHeadersSpecMock);
            when(requestHeadersSpecMock.retrieve())
                    .thenReturn(responseSpecMock);
            when(responseSpecMock.bodyToMono(ACSResponse.class)) // something here is wrong
                    .thenReturn(acsResponseMonoMock);
            when(acsResponseMonoMock.block())
                    .thenReturn(acsResponse);

            //when
            Result actualACSResults = acsRepository.getResults(query);

            //then
            assertEquals(expectedResults, actualACSResults);
    }

}