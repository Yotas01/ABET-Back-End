package edu.javeriana.abetbackend.CRUD.Controllers;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.OutcomeCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.OutcomeFinder;
import edu.javeriana.abetbackend.Common.Constants;
import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Entities.DTOs.OutcomeDTO;
import edu.javeriana.abetbackend.Exceptions.*;
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
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<OutcomeDTO> addCompetence(@RequestBody OutcomeDTO outcome){
        crudService.saveOutcome(outcome);
        return ResponseEntity.status(HttpStatus.CREATED).body(outcome);
    }

    @Operation(summary = "Find the ABET outcome with idOutcome")
    @GetMapping("/outcome/{idOutcome}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<OutcomeDTO> findCompetence(@PathVariable(value = "idOutcome") Integer id){
        Outcome outcome = finder.findOutcomeById(id);
        OutcomeDTO outcomeDTO = new OutcomeDTO(outcome);
        return ResponseEntity.status(HttpStatus.OK).body(outcomeDTO);
    }

    @Operation(summary = "Get all the ABET outcomes")
    @GetMapping("/outcome")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<List<OutcomeDTO>> getAllCompetences(){
        List<Outcome> outcomes = finder.getAllOutcomes();
        List<OutcomeDTO> outcomeDTOS = new ArrayList<>();
        outcomes.forEach(outcome -> outcomeDTOS.add(new OutcomeDTO(outcome)));
        return ResponseEntity.status(HttpStatus.OK).body(outcomeDTOS);
    }

    @Operation(summary = "Update an ABET outcome that matches the outcome's id")
    @PutMapping("/outcome/{outcomeId}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<OutcomeDTO> updateCompetence(@RequestBody OutcomeDTO outcome,
                                                       @PathVariable(name = "outcomeId") Integer outcomeId){
        Outcome updatedOutcome = crudService.updateOutcome(outcome, outcomeId);
        OutcomeDTO outcomeDTO = new OutcomeDTO(updatedOutcome);
        return ResponseEntity.status(HttpStatus.OK).body(outcomeDTO);
    }

    @Operation(summary = "Delete the ABET outcome that matches the id")
    @DeleteMapping("/outcome/{idOutcome}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<OutcomeDTO> deleteCompetence(@PathVariable(value = "idOutcome") Integer id){
        Outcome outcomeToDelete = finder.findOutcomeById(id);
        crudService.deleteOutcome(outcomeToDelete);
        OutcomeDTO outcomeDTO = new OutcomeDTO();
        outcomeDTO.setId(id);
        outcomeDTO.setDescription(outcomeToDelete.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(outcomeDTO);
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
