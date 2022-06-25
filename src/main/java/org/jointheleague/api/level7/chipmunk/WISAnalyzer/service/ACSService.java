package org.jointheleague.api.level7.chipmunk.WISAnalyzer.service;

import org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository.ACSRepository;
import org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository.dto.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ACSService {
    private final ACSRepository acsRepository;

    public ACSService(ACSRepository acsRepository) {
        this.acsRepository = acsRepository;
    }

    public List<Result> getResults(String query) {
        return acsRepository.getResults(query);
    }
}
