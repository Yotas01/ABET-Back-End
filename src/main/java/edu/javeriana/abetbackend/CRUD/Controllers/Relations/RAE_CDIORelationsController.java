package edu.javeriana.abetbackend.CRUD.Controllers.Relations;

import edu.javeriana.abetbackend.CRUD.Services.Relations.RAE_CDIORelationsService;
import edu.javeriana.abetbackend.Common.Constants;
import edu.javeriana.abetbackend.Entities.DTOs.RAEDTO;
import edu.javeriana.abetbackend.Exceptions.*;
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
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<RAEDTO> addRAEToCDIO(@PathVariable(value = "cdioNumber") Float cdioNumber,
                                               @PathVariable(value = "raeId") Long raeId){
        relationsService.addRAEtoCDIO(cdioNumber, raeId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Operation(summary = "Delete a relation between a RAE and a CDIO competence")
    @DeleteMapping
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<RAEDTO> deleteRAEFromCDIO(@PathVariable(value = "cdioNumber") Float cdioNumber,
                                               @PathVariable(value = "raeId") Long raeId){
        relationsService.deleteRAEFromCDIO(cdioNumber, raeId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ExceptionHandler({DoesNotContain.class, Inconsistent.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ABETSystemException badRequestError(Exception e){ return  new ABETSystemException(e.getMessage(), 400);}

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ABETSystemException notFoundError(Exception e){ return  new ABETSystemException(e.getMessage(), 404);}

    @ExceptionHandler({AlreadyContains.class, AlreadyExists.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ABETSystemException conflictError(Exception e){ return  new ABETSystemException(e.getMessage(), 409);}
}
