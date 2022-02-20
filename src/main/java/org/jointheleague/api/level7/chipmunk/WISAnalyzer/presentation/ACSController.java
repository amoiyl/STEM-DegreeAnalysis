package org.jointheleague.api.level7.chipmunk.WISAnalyzer.presentation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.jointheleague.api.level7.chipmunk.WISAnalyzer.service.ACSService;

@RestController
public class ACSController {

    private final ACSService acsService;

    public ACSController(ACSService locService) {
        this.acsService = locService;
    }

    @GetMapping("/searchACSResults")
    @ApiOperation(value = "Searches for women in the situation of the search term",
            notes = "Response may include multiple Result values.",
            response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Result(s) found"),
            @ApiResponse(code = 404, message = "Result(s) not found")
    })
    public String getResults(@RequestParam(value="q") String query){
        return acsService.getResults(query);
    }

}