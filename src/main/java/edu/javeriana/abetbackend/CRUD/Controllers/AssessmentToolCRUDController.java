package edu.javeriana.abetbackend.CRUD.Controllers;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.AssessmentToolCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.AssessmentToolFinder;
import edu.javeriana.abetbackend.Common.Constants;
import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.DTOs.AssessmentToolDTO;
import edu.javeriana.abetbackend.Exceptions.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/course/{courseNumber}/rae/{raeId}")
public class AssessmentToolCRUDController {

    @Autowired
    private AssessmentToolCRUD assessmentToolService;
    @Autowired
    private AssessmentToolFinder assessmentToolFinder;

    @Operation(summary = "Create an assessment tool")
    @PostMapping("/assessmentTool")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<AssessmentToolDTO> addAssessmentTool(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                               @PathVariable(name = "raeId") Long raeId,
                                                               @RequestBody AssessmentTool assessmentTool){
        assessmentToolService.saveAssessmentTool(assessmentTool,raeId,courseNumber);
        AssessmentToolDTO dto = new AssessmentToolDTO(assessmentTool);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "update an assessment tool")
    @PutMapping("/assessmentTool/{assessmentToolId}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<AssessmentToolDTO> updateAssessmentTool(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                                  @PathVariable(name = "raeId") Long raeId,
                                                                  @PathVariable(name = "assessmentToolId") Long assessmentToolId,
                                                                  @RequestBody AssessmentTool assessmentTool){
        AssessmentTool at = assessmentToolService.updateAssessmentTool(assessmentTool, raeId, courseNumber, assessmentToolId);
        AssessmentToolDTO dto = new AssessmentToolDTO(at);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Delete an assessment tool")
    @DeleteMapping("/assessmentTool/{assessmentToolId}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<AssessmentToolDTO> deleteAssessmentTool(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                                  @PathVariable(name = "raeId") Long raeId,
                                                                  @PathVariable(name = "assessmentToolId") Long assessmentToolId){
        AssessmentTool at = assessmentToolService.deleteAssessmentTool(assessmentToolId,raeId,courseNumber);
        AssessmentToolDTO dto = new AssessmentToolDTO(at);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Get an Assessment tool by id")
    @GetMapping("/assessmentTool/{assessmentToolId}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<AssessmentToolDTO> getAssessmentTool(@PathVariable(name = "assessmentToolId") Long assessmentToolId){
        AssessmentTool at = assessmentToolFinder.findById(assessmentToolId);
        AssessmentToolDTO dto = new AssessmentToolDTO(at);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Get all Assessment tool by Rae id")
    @GetMapping("/assessmentTool")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<List<AssessmentToolDTO>> getAssessmentToolByRAEId(@PathVariable(name = "raeId") Long raeId){
        List<AssessmentTool> assessmentTools = assessmentToolFinder.findAssessmentToolsByRAEId(raeId);
        List<AssessmentToolDTO> dto = new ArrayList<>();
        assessmentTools.forEach(at -> dto.add(new AssessmentToolDTO(at)));
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ExceptionHandler({DoesNotContain.class, Inconsistent.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badRequestError(Exception e){ return  e.getMessage();}

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundError(Exception e){ return  e.getMessage();}

    @ExceptionHandler({AlreadyContains.class, AlreadyExists.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictError(Exception e){ return  e.getMessage();}
}
