package org.jointheleague.api.level7.chipmunk.WISAnalyzer.service;

import org.jointheleague.api.level7.chipmunk.WISAnalyzer.presentation.ACSController;
import org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository.dto.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ACSServiceTest {
    private ACSController acsController;

    @Mock
    private ACSService acsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        acsController = new ACSController(acsService);
    }

    @Test
    void givenGoodQuery_whenGetResults_thenReturnListOfResults() {
        //given
        String query = "CA";
        Result result = new Result("", "", "");
        result.setState("California");
        result.setDegreeEarnedByWomen("10");
        result.setDegreeEarnedByMen("100");

        List<Result> expectedResults = Collections.singletonList(result);

        //when
        when(acsService.getResults(query)).thenReturn(expectedResults);
        List<Result> actualResults = acsController.getResults(query);

        //then
        assertEquals(expectedResults, actualResults);
    }
}