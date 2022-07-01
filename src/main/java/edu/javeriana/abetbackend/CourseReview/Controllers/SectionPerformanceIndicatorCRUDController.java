package edu.javeriana.abetbackend.CourseReview.Controllers;

import edu.javeriana.abetbackend.Common.Constants;
import edu.javeriana.abetbackend.CourseReview.Services.CRUD.SectionPerformanceIndicatorCRUD;
import edu.javeriana.abetbackend.CourseReview.Services.Find.SectionPerformanceIndicatorFinder;
import edu.javeriana.abetbackend.Entities.DTOs.SectionPerformanceIndicatorDTO;
import edu.javeriana.abetbackend.Entities.SectionPerformanceIndicator;
import edu.javeriana.abetbackend.Exceptions.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/course/{courseNumber}/section/{sectionNumber}/semester/{semester}/rae/{raeId}/assessmentTool" +
        "/{assessmentToolId}/sectionAT/{sectionATId}/performanceIndicator/{performanceIndicatorId}")
public class SectionPerformanceIndicatorCRUDController {

    @Autowired
    private SectionPerformanceIndicatorCRUD sectionPIService;
    @Autowired
    private SectionPerformanceIndicatorFinder sectionPIFinder;

    @Operation(description = "Add a new section performance indicator")
    @PostMapping("/sectionPI")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<SectionPerformanceIndicatorDTO> addSectionPI(@PathVariable Integer courseNumber,
                                                                       @PathVariable Integer sectionNumber,
                                                                       @PathVariable Long raeId,
                                                                       @PathVariable Long assessmentToolId,
                                                                       @PathVariable Long sectionATId,
                                                                       @PathVariable Integer semester,
                                                                       @PathVariable Long performanceIndicatorId,
                                                                       @RequestBody SectionPerformanceIndicator performanceIndicator){
        SectionPerformanceIndicator pi = sectionPIService.addSectionPerformanceIndicator(courseNumber, sectionNumber, semester,
                raeId, assessmentToolId, performanceIndicatorId, sectionATId, performanceIndicator);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SectionPerformanceIndicatorDTO(pi));
    }

    @Operation(description = "Update an existing section performance indicator")
    @PutMapping("/sectionPI/{sectionPIId}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<SectionPerformanceIndicatorDTO> updateSectionPI(@PathVariable Integer courseNumber,
                                                                          @PathVariable Integer sectionNumber,
                                                                          @PathVariable Long raeId,
                                                                          @PathVariable Long assessmentToolId,
                                                                          @PathVariable Long sectionATId,
                                                                          @PathVariable Integer semester,
                                                                          @PathVariable Long performanceIndicatorId,
                                                                          @PathVariable Long sectionPIId,
                                                                          @RequestBody SectionPerformanceIndicator performanceIndicator){
        SectionPerformanceIndicator pi = sectionPIService.updateSectionPerformanceIndicator(courseNumber, sectionNumber, semester,
                raeId, assessmentToolId, performanceIndicatorId, sectionATId, sectionPIId, performanceIndicator);
        return ResponseEntity.status(HttpStatus.OK).body(new SectionPerformanceIndicatorDTO(pi));
    }

    @Operation(description = "Delete an existing section performance indicator")
    @DeleteMapping("/sectionPI/{sectionPIId}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<SectionPerformanceIndicatorDTO> deleteSectionPI(@PathVariable Integer courseNumber,
                                                                          @PathVariable Integer sectionNumber,
                                                                          @PathVariable Long raeId,
                                                                          @PathVariable Long assessmentToolId,
                                                                          @PathVariable Long sectionATId,
                                                                          @PathVariable Integer semester,
                                                                          @PathVariable Long performanceIndicatorId,
                                                                          @PathVariable Long sectionPIId){
        SectionPerformanceIndicator pi = sectionPIService.deleteSectionPerformanceIndicator(courseNumber, sectionNumber, semester,
                raeId, assessmentToolId, performanceIndicatorId, sectionATId, sectionPIId);
        return ResponseEntity.status(HttpStatus.OK).body(new SectionPerformanceIndicatorDTO(pi));
    }

    @Operation(description = "Get all the section Performance Indicators from a section assessment tool")
    @GetMapping("/sectionPI")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<List<SectionPerformanceIndicatorDTO>> getSectionPIsFromSectionAT(@PathVariable Integer courseNumber,
                                                                                           @PathVariable Integer sectionNumber,
                                                                                           @PathVariable Long raeId,
                                                                                           @PathVariable Long assessmentToolId,
                                                                                           @PathVariable Long sectionATId,
                                                                                           @PathVariable Integer semester,
                                                                                           @PathVariable Long performanceIndicatorId){
        List<SectionPerformanceIndicator> SPIs = sectionPIFinder
                .findSectionPerformanceIndicatorsBySectionAssessmentTool(courseNumber,sectionNumber,semester,raeId,assessmentToolId,sectionATId);
        List<SectionPerformanceIndicatorDTO> dtoList = new ArrayList<>();
        SPIs.forEach(spi -> dtoList.add(new SectionPerformanceIndicatorDTO(spi)));
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @ExceptionHandler({DoesNotContain.class, Inconsistent.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ABETSystemException badRequestError(Exception e){ return  new ABETSystemException(e.getMessage());}

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ABETSystemException notFoundError(Exception e){ return  new ABETSystemException(e.getMessage());}

    @ExceptionHandler({AlreadyContains.class, AlreadyExists.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ABETSystemException conflictError(Exception e){ return  new ABETSystemException(e.getMessage());}
}
