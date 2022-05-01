package org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class ACSResponse {

    private String[][] results = null;

    @JsonProperty("results")
    public String[][] getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(String[][] results) {
        this.results = results;
    }

}