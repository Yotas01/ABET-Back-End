package edu.javeriana.abetbackend.CRUD.Controllers.Relations;

import edu.javeriana.abetbackend.CRUD.Services.Relations.Outcome_CDIORelationsService;
import edu.javeriana.abetbackend.Entities.DTOs.OutcomeDTO;
import edu.javeriana.abetbackend.Exceptions.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class Outcome_CDIORelationsController {

    @Autowired
    private Outcome_CDIORelationsService relationsService;

    @Operation(summary = "Create a relationship between an ABET outcome and a CDIO competence")
    @PutMapping("/outcome/{idOutcome}/{cdioNumber}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<OutcomeDTO> addCDIOToOutcome(@PathVariable(value = "idOutcome") Integer idOutcome,
                                                       @PathVariable(value = "cdioNumber") Float idCDIO){
        relationsService.addCDIOToOutcome(idOutcome, idCDIO);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Operation(summary = "Delete a relation between an ABET and a CDIO Competence")
    @DeleteMapping("/outcome/{idOutcome}/{cdioNumber}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<OutcomeDTO> deleteCDIOFromOutcome(@PathVariable(value = "idOutcome") Integer idOutcome,
                                                            @PathVariable(value = "cdioNumber") Float idCDIO){
        relationsService.deleteCDIOFromOutcome(idOutcome, idCDIO);
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
