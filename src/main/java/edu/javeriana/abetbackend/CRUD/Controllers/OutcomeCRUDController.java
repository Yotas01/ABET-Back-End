package edu.javeriana.abetbackend.CRUD.Controllers;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.OutcomeCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.OutcomeFinder;
import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Entities.DTOs.OutcomeDTO;
import edu.javeriana.abetbackend.Exceptions.AlreadyExists.OutcomeAlreadyExists;
import edu.javeriana.abetbackend.Exceptions.NotFound.OutcomeNotFoundById;
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
    public ResponseEntity<OutcomeDTO> addCompetence(@RequestBody Outcome outcome){
        System.out.println(outcome.toString());
        crudService.saveOutcome(outcome);
        OutcomeDTO outcomeDTO = new OutcomeDTO(outcome);
        return ResponseEntity.status(HttpStatus.CREATED).body(outcomeDTO);
    }

    @Operation(summary = "Find the ABET outcome with idOutcome")
    @GetMapping("/outcome/{idOutcome}")
    public ResponseEntity<OutcomeDTO> findCompetence(@PathVariable(value = "idOutcome") Integer id){
        Outcome outcome = finder.findOutcomeById(id);
        OutcomeDTO outcomeDTO = new OutcomeDTO(outcome);
        return ResponseEntity.status(HttpStatus.OK).body(outcomeDTO);
    }

    @Operation(summary = "Get all the ABET outcomes")
    @GetMapping("/outcome")
    public ResponseEntity<List<OutcomeDTO>> getAllCompetences(){
        List<Outcome> outcomes = finder.getAllOutcomes();
        List<OutcomeDTO> outcomeDTOS = new ArrayList<>();
        outcomes.forEach(outcome -> outcomeDTOS.add(new OutcomeDTO(outcome)));
        return ResponseEntity.status(HttpStatus.OK).body(outcomeDTOS);
    }

    @Operation(summary = "Update an ABET outcome that matches the outcome's id")
    @PutMapping("/outcome/{outcomeId}")
    public ResponseEntity<OutcomeDTO> updateCompetence(@RequestBody Outcome outcome,
                                                       @PathVariable(name = "outcomeId") Integer outcomeId){
        Outcome updatedOutcome = crudService.updateOutcome(outcome, outcomeId);
        OutcomeDTO outcomeDTO = new OutcomeDTO(updatedOutcome);
        return ResponseEntity.status(HttpStatus.OK).body(outcomeDTO);
    }

    @Operation(summary = "Delete the ABET outcome that matches the id")
    @DeleteMapping("/outcome/{idOutcome}")
    public ResponseEntity<OutcomeDTO> deleteCompetence(@PathVariable(value = "idOutcome") Integer id){
        Outcome outcomeToDelete = finder.findOutcomeById(id);
        crudService.deleteOutcome(outcomeToDelete);
        OutcomeDTO outcomeDTO = new OutcomeDTO(outcomeToDelete);
        return ResponseEntity.status(HttpStatus.OK).body(outcomeDTO);
    }

    @ExceptionHandler(OutcomeNotFoundById.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFoundError(Exception exception){
        return exception.getMessage();
    }
    @ExceptionHandler(OutcomeAlreadyExists.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String conflictError(Exception exception){
        return exception.getMessage();
    }
}
