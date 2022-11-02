package edu.javeriana.abetbackend.UseCases.CRUD.Controllers.Relations;

import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.UseCases.BaseController;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Relations.RAE_CDIORelationsService;
import edu.javeriana.abetbackend.Entities.DTOs.RAEDTO;
import edu.javeriana.abetbackend.Exceptions.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/admin/cdio/{cdioNumber}/rae/{raeId}")
@CrossOrigin(origins = "*")
public class RAE_CDIORelationsController extends BaseController {

    @Autowired
    private RAE_CDIORelationsService relationsService;

    @ApiOperation(value = "Create a relation between a RAE and a CDIO competence")
    @PostMapping
    public ResponseEntity<RAEDTO> addRAEToCDIO(@ApiParam(name = "cdioNumber", required = true)
                                                   @PathVariable Float cdioNumber,
                                               @ApiParam(name = "raeId", required = true)
                                               @PathVariable Long raeId){
        RAE rae = relationsService.addRAEtoCDIO(cdioNumber, raeId);
        return ResponseEntity.status(HttpStatus.OK).body(new RAEDTO(rae));
    }

    @ApiOperation(value = "Delete a relation between a RAE and a CDIO competence")
    @DeleteMapping
    public ResponseEntity<RAEDTO> deleteRAEFromCDIO(@ApiParam(name = "cdioNumber", required = true)
                                                        @PathVariable Float cdioNumber,
                                                    @ApiParam(name = "raeId", required = true)
                                                        @PathVariable Long raeId){
        RAE rae = relationsService.deleteRAEFromCDIO(cdioNumber, raeId);
        return ResponseEntity.status(HttpStatus.OK).body(new RAEDTO(rae));
    }
}
