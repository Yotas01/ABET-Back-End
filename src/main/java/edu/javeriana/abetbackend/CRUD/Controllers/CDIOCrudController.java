package edu.javeriana.abetbackend.CRUD.Controllers;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.CDIOCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Entities.ResponseEntities.ResponseCDIO;
import edu.javeriana.abetbackend.Exceptions.CDIOAlreadyExists;
import edu.javeriana.abetbackend.Exceptions.CDIONotFoundById;
import edu.javeriana.abetbackend.Exceptions.CDIONotFoundByNumber;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Basic;
import javax.persistence.ManyToMany;
import java.sql.PreparedStatement;
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
    public ResponseEntity<ResponseCDIO> addCDIO(@RequestBody CDIO cdio){
        crudService.saveCDIO(cdio);
        ResponseCDIO responseCDIO = new ResponseCDIO(cdio);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCDIO);
    }

    @Operation(summary = "Find the CDIO competence with cdioNumber")
    @GetMapping("/cdio/{cdioNumber}")
    public ResponseEntity<ResponseCDIO> findCDIO(@PathVariable(value = "cdioNumber") Float id){
        CDIO cdio = finder.findCDIOByNumber(id);
        ResponseCDIO responseCDIO = new ResponseCDIO(cdio);
        return ResponseEntity.status(HttpStatus.OK).body(responseCDIO);
    }

    @Operation(summary = "Get all the CDIO competences")
    @GetMapping("/cdio")
    public ResponseEntity<List<ResponseCDIO>> getAllCDIOs(){
        List<CDIO> cdios = finder.getAllCDIOs();
        List<ResponseCDIO> responseCDIOS= new ArrayList<>();
        cdios.forEach(cdio -> responseCDIOS.add(new ResponseCDIO(cdio)));
        return ResponseEntity.status(HttpStatus.OK).body(responseCDIOS);
    }

    @Operation(summary = "Update a CDIO competence that matches the cdio's number")
    @PutMapping("/cdio")
    public ResponseEntity<ResponseCDIO> updateCompetence(@RequestBody CDIO cdio){
        CDIO updatedCODIO = crudService.updateCDIO(cdio);
        ResponseCDIO responseCDIO = new ResponseCDIO(updatedCODIO);
        return ResponseEntity.status(HttpStatus.OK).body(responseCDIO);
    }

    @Operation(summary = "Delete the CDIO competence that matches the number")
    @DeleteMapping("/cdio/{cdioNumber}")
    public ResponseEntity<ResponseCDIO> deleteCompetence(@PathVariable(value = "cdioNumber") Float number){
        CDIO cdioToDelete = finder.findCDIOByNumber(number);
        CDIO deletedCDIO = crudService.deleteCDIO(cdioToDelete);
        ResponseCDIO responseCDIO = new ResponseCDIO(deletedCDIO);
        return ResponseEntity.status(HttpStatus.OK).body(responseCDIO);
    }

    @ExceptionHandler({CDIONotFoundById.class, CDIONotFoundByNumber.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundError(Exception exception){
        return exception.getMessage();
    }

    @ExceptionHandler(CDIOAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String alreadyExistsError(Exception exception){
        return exception.getMessage();
    }
}
