package edu.javeriana.abetbackend.CourseReview.Controllers;

import edu.javeriana.abetbackend.Common.Constants;
import edu.javeriana.abetbackend.CourseReview.Services.CRUD.SectionAssessmentToolCRUD;
import edu.javeriana.abetbackend.CourseReview.Services.Find.SectionAssessmentToolFinder;
import edu.javeriana.abetbackend.Entities.DTOs.SectionAssessmentToolDTO;
import edu.javeriana.abetbackend.Entities.SectionAssessmentTool;
import edu.javeriana.abetbackend.Exceptions.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/course/{courseNumber}/section/{sectionNumber}/semester/{semester}")
public class SectionAssessmentToolController {

    @Autowired
    private SectionAssessmentToolCRUD sectionAssessmentToolService;
    @Autowired
    private SectionAssessmentToolFinder sectionAssessmentToolFinder;

    @Operation(description = "Add a section assessment tool to a section")
    @PostMapping("/rae/{raeId}/assessmentTool/{assessmentToolId}/sectionAT")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<SectionAssessmentToolDTO> addSectionAssessmentTool(@PathVariable Integer courseNumber,
                                                                             @PathVariable Integer sectionNumber,
                                                                             @PathVariable Integer semester,
                                                                             @PathVariable Long raeId,
                                                                             @PathVariable Long assessmentToolId,
                                                                             @RequestBody SectionAssessmentTool sectionAssessmentTool){
        SectionAssessmentTool createdSAT = sectionAssessmentToolService
                .addSectionAssessmentTool(courseNumber, sectionNumber, semester, raeId, assessmentToolId, sectionAssessmentTool);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SectionAssessmentToolDTO(createdSAT));
    }

    @Operation(description = "Get all the sectionAT from a section")
    @GetMapping("/sectionAT")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<List<SectionAssessmentToolDTO>> getAllSectionATFromSection(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                                                     @PathVariable(name = "sectionNumber") Integer sectionNumber,
                                                                                     @PathVariable Integer semester){
        List<SectionAssessmentTool> assessmentTools = sectionAssessmentToolFinder
                .findAllSectionAssessmentToolsBySectionId(courseNumber, sectionNumber, semester);
        List<SectionAssessmentToolDTO> dtoList = new ArrayList<>();
        assessmentTools.forEach(sat -> dtoList.add(new SectionAssessmentToolDTO(sat)));
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @Operation(description = "Update a section assessment tool")
    @PutMapping("/rae/{raeId}/assessmentTool/{assessmentToolId}/sectionAT/{sectionATId}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<SectionAssessmentToolDTO> updateSectionAssessmentTool(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                                                @PathVariable(name = "sectionNumber") Integer sectionNumber,
                                                                                @PathVariable(name = "raeId") Long raeId,
                                                                                @PathVariable Integer semester,
                                                                                @PathVariable(name = "assessmentToolId") Long assessmentToolId,
                                                                                @PathVariable(name = "sectionATId") Long sectionAssessmentToolId,
                                                                                @RequestBody SectionAssessmentTool sectionAssessmentTool){
        SectionAssessmentTool updateSAT = sectionAssessmentToolService
                .updateSectionAssessmentTool(courseNumber, sectionNumber, semester, raeId, assessmentToolId, sectionAssessmentToolId, sectionAssessmentTool);
        return ResponseEntity.status(HttpStatus.OK).body(new SectionAssessmentToolDTO(updateSAT));
    }

    @Operation(description = "Delete a section assessment tool to a section")
    @DeleteMapping("/rae/{raeId}/assessmentTool/{assessmentToolId}/sectionAT/{sectionATId}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity deleteSectionAssessmentTool(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                      @PathVariable(name = "sectionNumber") Integer sectionNumber,
                                                      @PathVariable(name = "raeId") Long raeId,
                                                      @PathVariable(name = "assessmentToolId") Long assessmentToolId,
                                                      @PathVariable(name = "sectionATId") Long sectionAssessmentToolId,
                                                      @PathVariable Integer semester){
        sectionAssessmentToolService
                .deleteSectionAssessmentTool(courseNumber, sectionNumber, semester, raeId, assessmentToolId, sectionAssessmentToolId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
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
