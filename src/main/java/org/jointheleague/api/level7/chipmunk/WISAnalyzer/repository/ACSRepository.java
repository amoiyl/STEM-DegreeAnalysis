package org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository;

import org.springframework.stereotype.Repository;

public class ACSRepository {
    public String getResults(String query){
        return "Searching for the " + query + " STEM population.";
    }
}
