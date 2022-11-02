package edu.javeriana.abetbackend.UseCases.CRUD.Controllers.Relations;

import edu.javeriana.abetbackend.UseCases.BaseController;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Relations.Course_CDIORelationService;
import edu.javeriana.abetbackend.Entities.Course_has_CDIO;
import edu.javeriana.abetbackend.Entities.DTOs.CourseDTO;
import edu.javeriana.abetbackend.Entities.DTOs.Course_has_CDIODTO;
import edu.javeriana.abetbackend.Entities.DTOs.ValueDTO;
import edu.javeriana.abetbackend.Exceptions.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/course/{courseNumber}/cdio/{cdioNumber}")
@CrossOrigin("*")
public class Course_CDIORelationsController extends BaseController {

    @Autowired
    private Course_CDIORelationService relationService;

    @ApiOperation(value = "Make a relation between a Course and a CDIO competence")
    @PostMapping
    public ResponseEntity<CourseDTO> addCDIOToCourse(@ApiParam(name = "courseNumber", required = true)
                                                        @PathVariable Integer courseNumber,
                                                          @ApiParam(name = "cdioNumber", required = true)
                                                     @PathVariable Float cdioNumber,
                                                     @RequestBody ValueDTO value){
        CourseDTO dto = new CourseDTO(relationService.addCDIOToCourse(courseNumber,cdioNumber, value.getValue()));
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "Update the bloom value of a relation between a Course and a CDIO competence")
    @PutMapping
    public ResponseEntity<Course_has_CDIODTO> updateCDIOHasCourse(@ApiParam(name = "courseNumber", required = true)
                                                                      @PathVariable Integer courseNumber,
                                                                  @ApiParam(name = "cdioNumber", required = true)
                                                                      @PathVariable Float cdioNumber,
                                                                  @RequestBody ValueDTO value){
        Course_has_CDIO course_has_cdio = relationService.updateCDIOHasCourse(courseNumber,cdioNumber, value.getValue());
        return ResponseEntity.status(HttpStatus.OK).body(new Course_has_CDIODTO(course_has_cdio));
    }

    @Operation(summary = "Make a relation between a Course and a CDIO competence")
    @GetMapping()
    public ResponseEntity<Course_has_CDIODTO> getCourseHasCDIO(@ApiParam(name = "courseNumber", required = true)
                                                                   @PathVariable Integer courseNumber,
                                                               @ApiParam(name = "cdioNumber", required = true)
                                                                   @PathVariable Float cdioNumber){
        Course_has_CDIODTO course_has_cdio = new Course_has_CDIODTO(relationService.getCourse_has_cdio(courseNumber,cdioNumber));
        return ResponseEntity.status(HttpStatus.OK).body(course_has_cdio);
    }

    @Operation(summary = "Delete a relation between a Course and a CDIO competence")
    @DeleteMapping()
    public ResponseEntity<CourseDTO> deleteCDIOFromCourse(@ApiParam(name = "courseNumber", required = true)
                                                              @PathVariable Integer courseNumber,
                                                          @ApiParam(name = "cdioNumber", required = true)
                                                              @PathVariable Float cdioNumber){
        CourseDTO dto = new CourseDTO(relationService.deleteCDIOFromCourse(courseNumber,cdioNumber));
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
