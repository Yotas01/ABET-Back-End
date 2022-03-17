package edu.javeriana.abetbackend.CRUD.Controllers;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.PerformanceIndicatorCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.PerformanceIndicatorFinder;
import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.DTOs.AssessmentToolDTO;
import edu.javeriana.abetbackend.Entities.DTOs.PerformanceIndicatorDTO;
import edu.javeriana.abetbackend.Entities.PerformanceIndicator;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain.FoundAssessmentToolDoesNotContainFoundPerformanceIndicator;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain.FoundCourseDoesNotContainFoundRae;
import edu.javeriana.abetbackend.Exceptions.NotFound.PerformanceIndicatorNotFoundById;
import edu.javeriana.abetbackend.Exceptions.NotFound.PerformanceIndicatorsNotFoundByAssessmentTool;
import edu.javeriana.abetbackend.Exceptions.NotFound.RAENotFoundByCourse;
import edu.javeriana.abetbackend.Exceptions.NotFound.RAENotFoundById;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/course/{courseNumber}/rae/{raeId}/assessmentTool/{assessmentToolId}")
public class PerformanceIndicatorCRUDController {

    @Autowired
    private PerformanceIndicatorCRUD performanceIndicatorService;
    @Autowired
    private PerformanceIndicatorFinder performanceIndicatorFinder;

    @Operation(summary = "Create a performance indicator")
    @PostMapping("/performanceIndicator")
    public ResponseEntity<PerformanceIndicatorDTO> addPerformanceIndicator(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                                     @PathVariable(name = "raeId") Long raeId,
                                                                     @PathVariable(name = "assessmentToolId") Long assessmentToolId,
                                                                     @RequestBody PerformanceIndicator performanceIndicator){
        performanceIndicatorService.addPerformanceIndicator(performanceIndicator,courseNumber,raeId,assessmentToolId);
        PerformanceIndicatorDTO dto = new PerformanceIndicatorDTO(performanceIndicator);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "Update a performance indicator")
    @PutMapping("/performanceIndicator/{performanceIndicatorId}")
    public ResponseEntity<PerformanceIndicatorDTO> updatePerformanceIndicator(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                                  @PathVariable(name = "raeId") Long raeId,
                                                                  @PathVariable(name = "assessmentToolId") Long assessmentToolId,
                                                                  @PathVariable(name = "performanceIndicatorId") Long performanceIndicatorId,
                                                                  @RequestBody PerformanceIndicator performanceIndicator){
        PerformanceIndicator performanceIndicatorToUpdate = performanceIndicatorService
                .updatePerformanceIndicator(performanceIndicator,courseNumber,raeId,assessmentToolId,performanceIndicatorId);
        PerformanceIndicatorDTO dto = new PerformanceIndicatorDTO(performanceIndicatorToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Delete a performance indicator")
    @DeleteMapping("/performanceIndicator/{performanceIndicatorId}")
    public ResponseEntity<PerformanceIndicatorDTO> deletePerformanceIndicator(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                                              @PathVariable(name = "raeId") Long raeId,
                                                                              @PathVariable(name = "performanceIndicatorId") Long performanceIndicatorId,
                                                                              @PathVariable(name = "assessmentToolId") Long assessmentToolId){
        PerformanceIndicator pi = performanceIndicatorService
                .deletePerformanceIndicator(courseNumber,raeId,assessmentToolId,performanceIndicatorId);
        PerformanceIndicatorDTO dto = new PerformanceIndicatorDTO(pi);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Delete a performance indicator")
    @DeleteMapping("/performanceIndicator")
    public ResponseEntity<PerformanceIndicatorDTO> deletePerformanceIndicatorsFromAssessmentTool(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                                              @PathVariable(name = "raeId") Long raeId,
                                                                              @PathVariable(name = "assessmentToolId") Long assessmentToolId){
        performanceIndicatorService
                .deleteAllPerformanceIndicatorsFromAssessmentTool(courseNumber,raeId,assessmentToolId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Operation(summary = "Get a performance indicator")
    @GetMapping("/performanceIndicator/{performanceIndicatorId}")
    public ResponseEntity<PerformanceIndicatorDTO> getPerformanceIndicator(@PathVariable(name = "performanceIndicatorId") Long performanceIndicatorId){
        PerformanceIndicator pi = performanceIndicatorFinder.findPerformanceIndicatorById(performanceIndicatorId);
        PerformanceIndicatorDTO dto = new PerformanceIndicatorDTO(pi);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Get all performance indicator by Assessment tool id")
    @GetMapping("/performanceIndicator")
    public ResponseEntity<List<PerformanceIndicatorDTO>> getPerformanceIndicatorByAssessmentToolId(@PathVariable(name = "assessmentToolId") Long assessmentToolId){
        List<PerformanceIndicator> pis = performanceIndicatorFinder.getPerformanceIndicatorsFromAssessmentTool(assessmentToolId);
        List<PerformanceIndicatorDTO> dto = new ArrayList<>();
        pis.forEach(pi -> dto.add(new PerformanceIndicatorDTO(pi)));
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ExceptionHandler({RAENotFoundById.class, RAENotFoundByCourse.class, PerformanceIndicatorNotFoundById.class ,
            PerformanceIndicatorsNotFoundByAssessmentTool.class, FoundAssessmentToolDoesNotContainFoundPerformanceIndicator.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFoundError(Exception exception){
        return exception.getMessage();
    }
    @ExceptionHandler({FoundAssessmentToolDoesNotContainFoundPerformanceIndicator.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String doesNotContainError(Exception exception){
        return exception.getMessage();
    }
}
