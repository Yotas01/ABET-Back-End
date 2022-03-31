package edu.javeriana.abetbackend.CRUD.Controllers;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.RAECRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.RAEFinder;
import edu.javeriana.abetbackend.Entities.DTOs.DescriptionDTO;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Entities.DTOs.RAEDTO;
import edu.javeriana.abetbackend.Exceptions.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/course/{courseNumber}")
public class RAECRUDController {

    @Autowired
    private RAECRUD raeCRUDService;
    @Autowired
    private RAEFinder raeFinder;

    @Operation(summary = "Create a new RAE")
    @PostMapping("/rae")
    public ResponseEntity<RAEDTO> addRAE(@PathVariable(value = "courseNumber") Integer courseNumber,
                                         @RequestBody RAE rae){
        raeCRUDService.saveRAE(rae, courseNumber);
        RAEDTO responseRAE = new RAEDTO(rae);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseRAE);
    }

    @Operation(summary = "Find the RAEs from the Course with the corresponding courseNumber")
    @GetMapping("/rae")
    public ResponseEntity<List<RAEDTO>> getRAEsFromCourse(@PathVariable(value = "courseNumber") Integer courseNumber){
        List<RAE> raes = raeFinder.findRAEsFromCourseNumber(courseNumber);
        List<RAEDTO> responseRAEs = new ArrayList<>();
        raes.forEach(rae -> responseRAEs.add(new RAEDTO(rae)));
        return ResponseEntity.status(HttpStatus.OK).body(responseRAEs);
    }

    @Operation(summary = "Update a RAE description that matches the description and courseNumber")
    @PatchMapping("/rae/{raeId}")
    public ResponseEntity updateRAEDescription(@RequestBody DescriptionDTO descriptionDTO,
                                               @PathVariable(value = "raeId") Long raeId,
                                               @PathVariable(value = "courseNumber") Integer courseNumber){
        raeCRUDService.updateRAE(courseNumber, raeId, descriptionDTO.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Operation(summary = "Delete the RAE that matches the id")
    @DeleteMapping("/rae/{raeId}")
    public ResponseEntity deleteRAE(@PathVariable(value = "raeId") Long raeId,
                                            @PathVariable(value = "courseNumber") Integer courseNumber){
        raeCRUDService.deleteRAE(courseNumber, raeId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
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
