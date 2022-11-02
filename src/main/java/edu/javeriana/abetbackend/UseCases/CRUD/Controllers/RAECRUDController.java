package edu.javeriana.abetbackend.UseCases.CRUD.Controllers;

import edu.javeriana.abetbackend.UseCases.BaseController;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD.RAECRUD;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.RAEFinder;
import edu.javeriana.abetbackend.Entities.DTOs.DescriptionDTO;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Entities.DTOs.RAEDTO;
import edu.javeriana.abetbackend.Exceptions.*;
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
@RequestMapping(value = "/admin/course/{courseNumber}")
@CrossOrigin(origins = "*")
public class RAECRUDController extends BaseController {

    @Autowired
    private RAECRUD raeCRUDService;
    @Autowired
    private RAEFinder raeFinder;

    @ApiOperation(value = "Add a RAE to a course")
    @PostMapping("/rae")
    public ResponseEntity<RAEDTO> addRAE(@ApiParam(name = "courseNumber", required = true)
                                             @PathVariable Integer courseNumber,
                                         @RequestBody RAEDTO raedto){
        RAE rae = raeCRUDService.createRAE(raedto, courseNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RAEDTO(rae));
    }

    @ApiOperation(value = "Get all the RAEs from the Course with the corresponding courseNumber")
    @GetMapping("/rae/semester/{semester}")
    public ResponseEntity<List<RAEDTO>> getRAEsFromCourse(@ApiParam(name = "courseNumber", required = true)
                                                              @PathVariable Integer courseNumber,
                                                          @ApiParam(name = "semester", required = true)
                                                          @PathVariable Integer semester){
        List<RAE> raes = raeFinder.findRAEsFromCourseNumber(courseNumber, semester);
        List<RAEDTO> responseRAEs = new ArrayList<>();
        raes.forEach(rae -> responseRAEs.add(new RAEDTO(rae)));
        return ResponseEntity.status(HttpStatus.OK).body(responseRAEs);
    }

    @ApiOperation(value = "Find the RAE with id raeId")
    @GetMapping("/rae/{raeId}")
    public ResponseEntity<RAEDTO> getRAEFromCourse(@ApiParam(name = "courseNumber", required = false)
                                                       @PathVariable Integer courseNumber,
                                                   @ApiParam(name = "raeId", required = true)
                                                   @PathVariable Long raeId){
        RAE rae = raeFinder.findRAEById(raeId);
        return ResponseEntity.status(HttpStatus.OK).body(new RAEDTO(rae));
    }

    @ApiOperation(value = "Update a RAE description and semester that matches the and courseNumber and id")
    @PutMapping("/rae/{raeId}")
    public ResponseEntity<RAEDTO> updateRAEDescription(@RequestBody RAEDTO raedto,
                                               @ApiParam(name = "raeId", required = true)
                                               @PathVariable Long raeId,
                                               @ApiParam(name = "courseNumber", required = true)
                                                   @PathVariable Integer courseNumber){
        RAE rae = raeCRUDService.updateRAE(courseNumber, raeId, raedto);
        return ResponseEntity.status(HttpStatus.OK).body(new RAEDTO(rae));
    }

    @ApiOperation(value = "Delete the RAE that matches the id")
    @DeleteMapping("/rae/{raeId}")
    public ResponseEntity<RAEDTO> deleteRAE(@ApiParam(name = "raeId", required = true)
                                        @PathVariable Long raeId,
                                    @ApiParam(name = "courseNumber", required = true)
                                    @PathVariable Integer courseNumber){
        RAE rae = raeCRUDService.deleteRAE(courseNumber, raeId);
        return ResponseEntity.status(HttpStatus.OK).body(new RAEDTO(rae));
    }
}
