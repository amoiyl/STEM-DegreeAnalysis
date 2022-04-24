package org.jointheleague.api.level7.chipmunk.WISAnalyzer.presentation;

import org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository.Result;
import org.jointheleague.api.level7.chipmunk.WISAnalyzer.service.ACSService;
import org.jointheleague.api.level7.chipmunk.WISAnalyzer.presentation.ACSController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ACSControllerTest {

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

        String expectedResults = "In California, there are 100 STEM degrees earned by men and 10 STEM degrees earned by women.";

        when(acsService.getResults(query)).thenReturn(expectedResults);

        //when
        String actualResults = acsController.getResults(query);

        //then
        assertEquals(expectedResults, actualResults);
    }
}