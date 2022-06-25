package org.jointheleague.api.level7.chipmunk.WISAnalyzer.presentation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jointheleague.api.level7.chipmunk.WISAnalyzer.repository.dto.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.jointheleague.api.level7.chipmunk.WISAnalyzer.service.ACSService;

import java.util.List;

@RestController
@CrossOrigin
public class ACSController {

    private final ACSService acsService;

    public ACSController(ACSService locService) {
        this.acsService = locService;
    }

    @GetMapping("/searchACSResults")
    @ApiOperation(value = "Bachelor's degrees based on demographics\",\n" +
            "            notes = \"Response may include multiple Result values.",
            notes = "Response may include 1 Result value.",
            response = Result.class,
            responseContainer="String")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Result(s) found"),
            @ApiResponse(code = 404, message = "Result(s) not found")
    })

    public List<Result> getResults(@RequestParam(value="state") String query){
        return acsService.getResults(query);
    }

}