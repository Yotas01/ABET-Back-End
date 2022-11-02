package edu.javeriana.abetbackend.UseCases.CRUD.Controllers;

import edu.javeriana.abetbackend.UseCases.BaseController;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD.OutcomeCRUD;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.OutcomeFinder;
import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Entities.DTOs.OutcomeDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin(origins = "*")
public class OutcomeCRUDController extends BaseController {

    @Autowired
    private OutcomeCRUD crudService;
    @Autowired
    private OutcomeFinder finder;

    @ApiOperation(value = "Create a new ABET outcome")
    @PostMapping("/outcome")
    public ResponseEntity<OutcomeDTO> addOutcome(@RequestBody OutcomeDTO outcome){
        Outcome createdOutcome = crudService.createOutcome(outcome);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OutcomeDTO(createdOutcome));
    }

    @ApiOperation(value = "Find the ABET outcome with idOutcome")
    @GetMapping("/outcome/{idOutcome}")
    public ResponseEntity<OutcomeDTO> findCompetence(@ApiParam(name = "idOutcome", required = true, defaultValue = "1")
                                                         @PathVariable Integer idOutcome){
        Outcome outcome = finder.findOutcomeById(idOutcome);
        return ResponseEntity.status(HttpStatus.OK).body(new OutcomeDTO(outcome));
    }

    @ApiOperation(value = "Get all the ABET outcomes")
    @GetMapping("/outcome")
    public ResponseEntity<List<OutcomeDTO>> getAllCompetences(){
        List<Outcome> outcomes = finder.getAllOutcomes();
        List<OutcomeDTO> outcomeDTOS = new ArrayList<>();
        outcomes.forEach(outcome -> outcomeDTOS.add(new OutcomeDTO(outcome)));
        return ResponseEntity.status(HttpStatus.OK).body(outcomeDTOS);
    }

    @ApiOperation(value = "Update an ABET outcome that matches the outcome's id")
    @PutMapping("/outcome/{outcomeId}")
    public ResponseEntity<OutcomeDTO> updateCompetence(@RequestBody OutcomeDTO outcome,
                                                       @ApiParam(name = "outcomeId", required = true)
                                                       @PathVariable Integer outcomeId){
        Outcome updatedOutcome = crudService.updateOutcome(outcome, outcomeId);
        return ResponseEntity.status(HttpStatus.OK).body(new OutcomeDTO(updatedOutcome));
    }

    @ApiOperation(value = "Delete the ABET outcome that matches the id")
    @DeleteMapping("/outcome/{idOutcome}")
    public ResponseEntity<OutcomeDTO> deleteCompetence(@ApiParam(name = "idOutcome", required = true) @PathVariable Integer idOutcome){
        Outcome outcome = crudService.deleteOutcome(idOutcome);
        return ResponseEntity.status(HttpStatus.OK).body(new OutcomeDTO(outcome));
    }
}
