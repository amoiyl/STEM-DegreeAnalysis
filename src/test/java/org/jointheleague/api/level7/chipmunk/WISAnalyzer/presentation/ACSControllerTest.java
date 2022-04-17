package org.jointheleague.api.level7.chipmunk.WISAnalyzer.presentation;

import org.jointheleague.api.level7.chipmunk.WISAnalyzer.service.ACSService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void getResults() {
        // given
        String query = "CA";
        String[] expectedResults = {"0", "0", "0"};

        when(acsService.getResults(query))
                .thenReturn(expectedResults);

        // when
        String[] actualResults = acsController.getResults(query);

        // then
        System.err.println((actualResults));
        assertEquals(expectedResults, actualResults);
    }
}