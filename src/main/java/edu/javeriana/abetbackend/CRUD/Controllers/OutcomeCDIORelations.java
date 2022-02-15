package edu.javeriana.abetbackend.CRUD.Controllers;

import edu.javeriana.abetbackend.CRUD.Services.Relations.OutcomeRelationsService;
import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Entities.ResponseEntities.ResponseOutcome;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class OutcomeCDIORelations {

    @Autowired
    private OutcomeRelationsService relationsService;

    @Operation(summary = "Create a relationship between an ABET outcome and a CDIO competence")
    @PutMapping("/outcome/{idOutcome}/{cdioNumber}")
    public ResponseEntity<ResponseOutcome> addCDIOToCompetence(@PathVariable(value = "idOutcome") Long idCompetence,
                                                               @PathVariable(value = "cdioNumber") Float idCDIO){
        Outcome outcome =  relationsService.addCDIOToOutcome(idCompetence, idCDIO);
        ResponseOutcome responseOutcome = new ResponseOutcome(outcome);
        return ResponseEntity.status(HttpStatus.OK).body(responseOutcome);
    }

    @Operation(summary = "Delete a relation between an ABET and a CDIO Competence")
    @DeleteMapping("/outcome/{idOutcome}/{cdioNumber}")
    public ResponseEntity<ResponseOutcome> deleteCDIOFromCompetence(@PathVariable(value = "idOutcome") Long idCompetence,
                                                   @PathVariable(value = "cdioNumber") Float idCDIO){
        Outcome deletedOutcome = relationsService.deleteCDIOFromOutcome(idCompetence, idCDIO);
        ResponseOutcome responseOutcome = new ResponseOutcome(deletedOutcome);
        return ResponseEntity.status(HttpStatus.OK).body(responseOutcome);
    }

}
