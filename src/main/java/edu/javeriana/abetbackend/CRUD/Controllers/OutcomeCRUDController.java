package edu.javeriana.abetbackend.CRUD.Controllers;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.OutcomeCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.OutcomeFinder;
import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Entities.ResponseEntities.ResponseOutcome;
import edu.javeriana.abetbackend.Exceptions.OutcomeNotFoundById;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class OutcomeCRUDController {

    @Autowired
    private OutcomeCRUD crudService;
    @Autowired
    private OutcomeFinder finder;

    @Operation(summary = "Create a new ABET outcome")
    @PostMapping("/outcome")
    public ResponseEntity<ResponseOutcome> addCompetence(@RequestBody Outcome outcome){
        crudService.saveOutcome(outcome);
        ResponseOutcome responseOutcome = new ResponseOutcome(outcome);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOutcome);
    }

    @Operation(summary = "Find the ABET outcome with idOutcome")
    @GetMapping("/outcome/{idOutcome}")
    public ResponseEntity<ResponseOutcome> findCompetence(@PathVariable(value = "idOutcome") Long id){
        Outcome outcome = finder.findOutcomeById(id);
        ResponseOutcome responseOutcome = new ResponseOutcome(outcome);
        return ResponseEntity.status(HttpStatus.OK).body(responseOutcome);
    }

    @Operation(summary = "Get all the ABET outcomes")
    @GetMapping("/outcome")
    public ResponseEntity<List<ResponseOutcome>> getAllCompetences(){
        List<Outcome> outcomes = finder.getAllOutcomes();
        List<ResponseOutcome> responseOutcomes = new ArrayList<>();
        outcomes.forEach(outcome -> responseOutcomes.add(new ResponseOutcome(outcome)));
        return ResponseEntity.status(HttpStatus.OK).body(responseOutcomes);
    }

    @Operation(summary = "Update an ABET outcome that matches the outcome's id")
    @PutMapping("/outcome")
    public ResponseEntity<ResponseOutcome> updateCompetence(@RequestBody Outcome outcome){
        Outcome updatedOutcome = crudService.updateOutcome(outcome);
        ResponseOutcome responseOutcome = new ResponseOutcome(outcome);
        return ResponseEntity.status(HttpStatus.OK).body(responseOutcome);
    }

    @Operation(summary = "Delete the ABET outcome that matches the id")
    @DeleteMapping("/outcome/{idOutcome}")
    public ResponseEntity<ResponseOutcome> deleteCompetence(@PathVariable(value = "idOutcome") Long id){
        Outcome outcomeToDelete = finder.findOutcomeById(id);
        crudService.deleteOutcome(outcomeToDelete);
        ResponseOutcome responseOutcome = new ResponseOutcome(outcomeToDelete);
        return ResponseEntity.status(HttpStatus.OK).body(responseOutcome);
    }

    @ExceptionHandler(OutcomeNotFoundById.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFoundError(Exception exception){
        return exception.getMessage();
    }
}
