package edu.javeriana.abetbackend.UseCases.CRUD.Controllers.Relations;

import edu.javeriana.abetbackend.UseCases.CRUD.Services.Relations.Course_CDIORelationService;
import edu.javeriana.abetbackend.Common.Constants;
import edu.javeriana.abetbackend.Entities.Course_has_CDIO;
import edu.javeriana.abetbackend.Entities.DTOs.CourseDTO;
import edu.javeriana.abetbackend.Entities.DTOs.Course_has_CDIODTO;
import edu.javeriana.abetbackend.Entities.DTOs.ValueDTO;
import edu.javeriana.abetbackend.Exceptions.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/course/{courseNumber}/cdio/{cdioNumber}")
public class Course_CDIORelationsController {

    @Autowired
    private Course_CDIORelationService relationService;

    @Operation(summary = "Make a relation between a Course and a CDIO competence")
    @PostMapping()
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<CourseDTO> addCDIOToCourse(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                           @PathVariable(name = "cdioNumber") Float cdioNumber,
                                                           @RequestBody ValueDTO value){
        Course_has_CDIO course_has_cdio = relationService.addCDIOToCourse(courseNumber,cdioNumber, value.getValue());
        CourseDTO dto = new CourseDTO(course_has_cdio.getId().getCourse());
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Make a relation between a Course and a CDIO competence")
    @PutMapping()
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<Course_has_CDIODTO> updateCDIOHasCourse(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                     @PathVariable(name = "cdioNumber") Float cdioNumber,
                                                     @RequestBody(required = false) ValueDTO value){
        Course_has_CDIO course_has_cdio = relationService.updateCDIOHasCourse(courseNumber,cdioNumber, value.getValue());
        return ResponseEntity.status(HttpStatus.OK).body(new Course_has_CDIODTO(course_has_cdio));
    }

    @Operation(summary = "Make a relation between a Course and a CDIO competence")
    @GetMapping()
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<Course_has_CDIODTO> getCourseHasCDIO(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                               @PathVariable(name = "cdioNumber") Float cdioNumber){
        Course_has_CDIODTO course_has_cdio = new Course_has_CDIODTO(relationService.getCourseHasCDIO(courseNumber,cdioNumber));
        return ResponseEntity.status(HttpStatus.OK).body(course_has_cdio);
    }

    @Operation(summary = "Delete a relation between a Course and a CDIO competence")
    @DeleteMapping()
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<CourseDTO> deleteCDIOFromCourse(@PathVariable(name = "courseNumber") Integer courseNumber,
                                                     @PathVariable(name = "cdioNumber") Float cdioNumber){
        Course_has_CDIO course_has_cdio = relationService.deleteCDIOFromCourse(courseNumber,cdioNumber);
        CourseDTO dto = new CourseDTO(course_has_cdio.getId().getCourse());
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ExceptionHandler({DoesNotContain.class, Inconsistent.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ABETSystemException badRequestError(Exception e){ return  new ABETSystemException(e.getMessage(), 400);}

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ABETSystemException notFoundError(Exception e){ return  new ABETSystemException(e.getMessage(), 404);}

    @ExceptionHandler({AlreadyContains.class, AlreadyExists.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ABETSystemException conflictError(Exception e){ return  new ABETSystemException(e.getMessage(), 409);}
}
