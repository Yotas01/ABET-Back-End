package edu.javeriana.abetbackend.UseCases.CRUD.Controllers.Relations;

import edu.javeriana.abetbackend.Entities.DTOs.PerformanceIndicatorDTO;
import edu.javeriana.abetbackend.Entities.PerformanceIndicator;
import edu.javeriana.abetbackend.UseCases.BaseController;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Relations.AT_PIRelationsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/assessmentTool/{assessmentToolId}/performanceIndicator/{performanceIndicatorId}")
@CrossOrigin(origins = "*")
public class AT_PIRelationsController extends BaseController {

    @Autowired
    private AT_PIRelationsService service;

    @ApiOperation(value = "Add a relation between an existing performance indicator and an existing assessment tool")
    @PostMapping
    public ResponseEntity<PerformanceIndicatorDTO> addAssessmentToolToPerformanceIndicator(@ApiParam(name = "assessmentToolId" ,required = true)
                                                                                               @PathVariable Long assessmentToolId,
                                                                                           @ApiParam(name = "performanceIndicatorId" ,required = true)
                                                                                           @PathVariable Long performanceIndicatorId){
        PerformanceIndicator performanceIndicator = service.addAssessmentToolToPerformanceIndicator(assessmentToolId, performanceIndicatorId);
        return ResponseEntity.ok(new PerformanceIndicatorDTO(performanceIndicator));
    }

    @ApiOperation(value = "Delete a relation between an existing performance indicator and an existing assessment tool")
    @DeleteMapping
    public ResponseEntity<PerformanceIndicatorDTO> removeAssessmentToolToPerformanceIndicator(@ApiParam(name = "assessmentToolId" ,required = true)
                                                                                           @PathVariable Long assessmentToolId,
                                                                                           @ApiParam(name = "performanceIndicatorId" ,required = true)
                                                                                           @PathVariable Long performanceIndicatorId){
        PerformanceIndicator performanceIndicator = service.deleteAssessmentToolFromPerformanceIndicator(assessmentToolId, performanceIndicatorId);
        return ResponseEntity.ok(new PerformanceIndicatorDTO(performanceIndicator));
    }
}
