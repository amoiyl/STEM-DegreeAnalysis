package org.jointheleague.api.level7.chipmunk.WISAnalyzer.service;

import org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository.ACSRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ACSService {
    private final ACSRepository acsRepository;

    public ACSService(ACSRepository acsRepository) {
        this.acsRepository = acsRepository;
    }

    public String[] getResults(String query) {
        String[][] filteredData = acsRepository.getResults();
        String[] requestedData = Arrays.stream(filteredData).filter(datum->datum[0].equals(query)).collect(Collectors.toList()).get(0);
        return requestedData;
    }
}
