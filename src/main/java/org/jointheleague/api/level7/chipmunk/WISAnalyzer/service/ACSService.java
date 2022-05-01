package org.jointheleague.api.level7.chipmunk.WISAnalyzer.service;

import org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository.ACSRepository;
import org.springframework.stereotype.Service;

@Service
public class ACSService {
    private final ACSRepository acsRepository;

    public ACSService(ACSRepository acsRepository) {
        this.acsRepository = acsRepository;
    }

    public String getResults(String query) {
        return acsRepository.getResults(query).toString();
    }
}
