package org.jointheleague.api.level7.chipmunk.WISAnalyzer.presentation;

import org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository.dto.Result;
import org.jointheleague.api.level7.chipmunk.WISAnalyzer.service.ACSService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ACSController.class)

class ACSControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ACSService acsService;

    @Test
    public void givenGoodQuery_whenSearchForResults_thenIsOkAndReturnsResults() throws Exception {
        //given
        String query = "California";
        Result result = new Result("", "", "");
        result.setState("California");
        result.setDegreeEarnedByWomen("10");
        result.setDegreeEarnedByMen("100");

        List<Result> expectedResults = Collections.singletonList(result);

        when(acsService.getResults(query)).thenReturn(expectedResults);

        //when
        //then
        MvcResult mvcResult = mockMvc.perform(get("/searchACSResults?state=" + query))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        mvcResult.getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);

        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE, mvcResult.getResponse().getContentType());
    }

    @Test
    public void givenBadQuery_whenSearchForResults_thenIsNotFound() throws Exception {
        //given
        String query = "not a state";

        //when
        when(acsService.getResults(query)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Result(s) not found."));

        //then
        mockMvc.perform(get("/searchACSResults?state=" + query))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}