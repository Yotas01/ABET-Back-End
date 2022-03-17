package edu.javeriana.abetbackend.CRUD.Controllers.Relations;

import edu.javeriana.abetbackend.CRUD.Services.Relations.Outcome_CDIORelationsService;
import edu.javeriana.abetbackend.Entities.DTOs.OutcomeDTO;
import edu.javeriana.abetbackend.Exceptions.NotFound.CDIONotFoundByNumber;
import edu.javeriana.abetbackend.Exceptions.AlreadyContains.OutcomeAlreadyContainsCDIO;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain.OutcomeDoesNotContainCDIO;
import edu.javeriana.abetbackend.Exceptions.NotFound.OutcomeNotFoundById;
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
    public ResponseEntity<OutcomeDTO> addCDIOToOutcome(@PathVariable(value = "idOutcome") Integer idOutcome,
                                                       @PathVariable(value = "cdioNumber") Float idCDIO){
        relationsService.addCDIOToOutcome(idOutcome, idCDIO);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Operation(summary = "Delete a relation between an ABET and a CDIO Competence")
    @DeleteMapping("/outcome/{idOutcome}/{cdioNumber}")
    public ResponseEntity<OutcomeDTO> deleteCDIOFromOutcome(@PathVariable(value = "idOutcome") Integer idOutcome,
                                                            @PathVariable(value = "cdioNumber") Float idCDIO){
        relationsService.deleteCDIOFromOutcome(idOutcome, idCDIO);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ExceptionHandler(OutcomeAlreadyContainsCDIO.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictOutcomeAlreadyContainsCDIO(Exception exception){return exception.getMessage();}
    @ExceptionHandler(OutcomeDoesNotContainCDIO.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String conflictOutcomeDoesNotContainCDIO(Exception exception){return exception.getMessage();}
    @ExceptionHandler({OutcomeNotFoundById.class, CDIONotFoundByNumber.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFoundError(Exception exception){
        return exception.getMessage();
    }
}
