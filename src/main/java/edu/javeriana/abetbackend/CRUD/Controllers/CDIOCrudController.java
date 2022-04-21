package edu.javeriana.abetbackend.CRUD.Controllers;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.CDIOCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.DTOs.CDIODTO;
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
public class CDIOCrudController {

    @Autowired
    private CDIOCRUD crudService;
    @Autowired
    private CDIOFinder finder;

    @Operation(summary = "Create a new CDIO competence")
    @PostMapping("/cdio")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CDIODTO> addCDIO(@RequestBody CDIO cdio){
        crudService.saveCDIO(cdio);
        CDIODTO CDIODTO = new CDIODTO(cdio);
        return ResponseEntity.status(HttpStatus.CREATED).body(CDIODTO);
    }

    @Operation(summary = "Find the CDIO competence with cdioNumber")
    @GetMapping("/cdio/{cdioNumber}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CDIODTO> findCDIO(@PathVariable(value = "cdioNumber") Float id){
        CDIO cdio = finder.findCDIOById(id);
        CDIODTO CDIODTO = new CDIODTO(cdio);
        return ResponseEntity.status(HttpStatus.OK).body(CDIODTO);
    }

    @Operation(summary = "Get all the CDIO competences")
    @GetMapping("/cdio")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<CDIODTO>> getAllCDIOs(){
        List<CDIO> cdios = finder.getAllCDIOs();
        List<CDIODTO> CDIODTOs = new ArrayList<>();
        cdios.forEach(cdio -> CDIODTOs.add(new CDIODTO(cdio)));
        return ResponseEntity.status(HttpStatus.OK).body(CDIODTOs);
    }

    @Operation(summary = "Update a CDIO competence that matches the cdio's number")
    @PutMapping("/cdio")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CDIODTO> updateCompetence(@RequestBody CDIO cdio){
        CDIO updatedCODIO = crudService.updateCDIO(cdio);
        CDIODTO CDIODTO = new CDIODTO(updatedCODIO);
        return ResponseEntity.status(HttpStatus.OK).body(CDIODTO);
    }

    @Operation(summary = "Delete the CDIO competence that matches the number")
    @DeleteMapping("/cdio/{cdioNumber}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CDIODTO> deleteCompetence(@PathVariable(value = "cdioNumber") Float cdioNumber){
        CDIO deletedCDIO = crudService.deleteCDIO(cdioNumber);
        CDIODTO CDIODTO = new CDIODTO(deletedCDIO);
        return ResponseEntity.status(HttpStatus.OK).body(CDIODTO);
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
