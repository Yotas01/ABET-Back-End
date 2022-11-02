package edu.javeriana.abetbackend.UseCases.CRUD.Controllers;

import edu.javeriana.abetbackend.UseCases.BaseController;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD.CDIOCRUD;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.DTOs.CDIODTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin(origins = "*")
public class CDIOCrudController extends BaseController {

    @Autowired
    private CDIOCRUD crudService;
    @Autowired
    private CDIOFinder finder;

    @ApiOperation(value = "Create a new CDIO competence")
    @PostMapping("/cdio")
    public ResponseEntity<CDIODTO> createCDIO(@RequestBody CDIODTO cdio){
        CDIO createdCDIO = crudService.createCDIO(cdio);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CDIODTO(createdCDIO));
    }

    @ApiOperation(value = "Find the CDIO competence with cdioNumber")
    @GetMapping("/cdio/{cdioNumber}")
    public ResponseEntity<CDIODTO> findCDIO(@ApiParam(name = "cdioNumber", required = true) @PathVariable Float cdioNumber){
        CDIO cdio = finder.findCDIOById(cdioNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new CDIODTO(cdio));
    }

    @ApiOperation(value = "Get all the CDIO competences")
    @GetMapping("/cdio")
    public ResponseEntity<List<CDIODTO>> getAllCDIOs(){
        List<CDIO> cdios = finder.getAllCDIOs();
        List<CDIODTO> CDIODTOs = new ArrayList<>();
        cdios.forEach(cdio -> CDIODTOs.add(new CDIODTO(cdio)));
        return ResponseEntity.status(HttpStatus.OK).body(CDIODTOs);
    }

    @Operation(summary = "Update a CDIO competence that matches the cdio's number")
    @PutMapping("/cdio/{cdioNumber}")
    public ResponseEntity<CDIODTO> updateCompetence(@RequestBody CDIODTO cdio,
                                                    @ApiParam(name = "cdioNumber", required = true)
                                                    @PathVariable Float cdioNumber){
        CDIO updatedCODIO = crudService.updateCDIO(cdio, cdioNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new CDIODTO(updatedCODIO));
    }

    @Operation(summary = "Delete the CDIO competence that matches the number")
    @DeleteMapping("/cdio/{cdioNumber}")
    public ResponseEntity<CDIODTO> deleteCompetence(@ApiParam(name = "cdioNumber", required = true)
                                                        @PathVariable Float cdioNumber){
        CDIO deletedCDIO = crudService.deleteCDIO(cdioNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new CDIODTO(deletedCDIO));
    }
}
