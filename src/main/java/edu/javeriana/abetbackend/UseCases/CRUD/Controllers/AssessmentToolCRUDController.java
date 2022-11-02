package edu.javeriana.abetbackend.UseCases.CRUD.Controllers;

import edu.javeriana.abetbackend.UseCases.BaseController;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD.AssessmentToolCRUD;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.AssessmentToolFinder;
import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.DTOs.AssessmentToolDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/course/{courseNumber}")
@CrossOrigin(origins = "*")
public class AssessmentToolCRUDController extends BaseController {

    @Autowired
    private AssessmentToolCRUD assessmentToolService;
    @Autowired
    private AssessmentToolFinder assessmentToolFinder;

    @ApiOperation(value = "Add an assessment tool to a course")
    @PostMapping("/assessmentTool")
    public ResponseEntity<AssessmentToolDTO> addAssessmentTool(@ApiParam(name = "courseNumber", required = true)
                                                                   @PathVariable Integer courseNumber,
                                                               @RequestBody AssessmentToolDTO dto){
        AssessmentTool assessmentTool = assessmentToolService.saveAssessmentTool(dto,courseNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AssessmentToolDTO(assessmentTool));
    }

    @ApiOperation(value = "Update an existing assessment tool")
    @PutMapping("/assessmentTool/{assessmentToolId}")
    public ResponseEntity<AssessmentToolDTO> updateAssessmentTool(@ApiParam(name = "courseNumber", required = true)
                                                                      @PathVariable(name = "courseNumber") Integer courseNumber,
                                                                  @ApiParam(name = "assessmentToolId", required = true)
                                                                  @PathVariable(name = "assessmentToolId") Long assessmentToolId,
                                                                  @RequestBody AssessmentToolDTO dto){
        AssessmentTool at = assessmentToolService.updateAssessmentTool(dto, courseNumber, assessmentToolId);
        return ResponseEntity.status(HttpStatus.OK).body(new AssessmentToolDTO(at));
    }

    @Operation(summary = "Delete an assessment tool")
    @DeleteMapping("/assessmentTool/{assessmentToolId}")
    public ResponseEntity<AssessmentToolDTO> deleteAssessmentTool(@ApiParam(name = "courseNumber", required = true)
                                                                      @PathVariable(name = "courseNumber") Integer courseNumber,
                                                                  @ApiParam(name = "assessmentToolId", required = true)
                                                                      @PathVariable(name = "assessmentToolId") Long assessmentToolId){
        AssessmentTool at = assessmentToolService.deleteAssessmentTool(assessmentToolId, courseNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new AssessmentToolDTO(at));
    }

    @Operation(summary = "Get an Assessment tool by id")
    @GetMapping("/assessmentTool/{assessmentToolId}")
    public ResponseEntity<AssessmentToolDTO> getAssessmentTool(@ApiParam(name = "courseNumber", required = false)
                                                                   @PathVariable(name = "courseNumber") Integer courseNumber,
                                                               @ApiParam(name = "assessmentToolId", required = true)
                                                                   @PathVariable(name = "assessmentToolId") Long assessmentToolId){
        AssessmentTool at = assessmentToolFinder.findById(assessmentToolId);
        return ResponseEntity.status(HttpStatus.OK).body(new AssessmentToolDTO(at));
    }

    @Operation(summary = "Get all Assessment tools from a course")
    @GetMapping("/assessmentTool")
    public ResponseEntity<List<AssessmentToolDTO>> getAssessmentToolByRAEId(@ApiParam(name = "courseNumber", required = true)
                                                                                @PathVariable(name = "courseNumber") Integer courseNumber){
        List<AssessmentTool> assessmentTools = assessmentToolFinder.getAllByCourse(courseNumber);
        List<AssessmentToolDTO> dto = new ArrayList<>(assessmentTools.stream().map(AssessmentToolDTO::new).toList());
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
