package edu.javeriana.abetbackend.CRUD.Controllers.Relations;

import edu.javeriana.abetbackend.CRUD.Services.Relations.RAE_CDIORelationsService;
import edu.javeriana.abetbackend.Entities.DTOs.DescriptionDTO;
import edu.javeriana.abetbackend.Entities.DTOs.RAEDTO;
import edu.javeriana.abetbackend.Exceptions.AlreadyContains.RAEAlreadyContainsCDIO;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain.RAEDoesNotContainCDIO;
import edu.javeriana.abetbackend.Exceptions.NotFound.CDIONotFoundByNumber;
import edu.javeriana.abetbackend.Exceptions.NotFound.RAENotFoundByCourseAndDescription;
import edu.javeriana.abetbackend.Exceptions.NotFound.RAENotFoundById;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/admin/cdio/{cdioNumber}/rae/{raeId}")
public class RAE_CDIORelationsController {

    @Autowired
    private RAE_CDIORelationsService relationsService;

    @Operation(summary = "Create a relation between a RAE and a CDIO competence")
    @PostMapping
    public ResponseEntity<RAEDTO> addRAEToCDIO(@PathVariable(value = "cdioNumber") Float cdioNumber,
                                               @PathVariable(value = "raeId") Long raeId){
        relationsService.addRAEtoCDIO(cdioNumber, raeId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Operation(summary = "Delete a relation between a RAE and a CDIO competence")
    @DeleteMapping
    public ResponseEntity<RAEDTO> deleteRAEFromCDIO(@PathVariable(value = "cdioNumber") Float cdioNumber,
                                               @PathVariable(value = "raeId") Long raeId){
        relationsService.deleteRAEFromCDIO(cdioNumber, raeId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ExceptionHandler(RAEAlreadyContainsCDIO.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictRAEAlreadyContainsCDIO(Exception exception){return exception.getMessage();}
    @ExceptionHandler(RAEDoesNotContainCDIO.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String conflictRAEDoesNotContainCDIO(Exception exception){return exception.getMessage();}
    @ExceptionHandler({RAENotFoundByCourseAndDescription.class, CDIONotFoundByNumber.class, RAENotFoundById.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFoundError(Exception exception){
        return exception.getMessage();
    }
}
