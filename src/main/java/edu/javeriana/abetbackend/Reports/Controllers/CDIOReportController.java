package edu.javeriana.abetbackend.Reports.Controllers;

import edu.javeriana.abetbackend.Entities.Views.CDIOReport;
import edu.javeriana.abetbackend.Exceptions.*;
import edu.javeriana.abetbackend.Reports.Services.CDIOReportService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/report")
public class CDIOReportController {

    @Autowired
    private CDIOReportService reportService;

    @GetMapping("/cdio/{cdioNumber}/semester/{semester}")
    @Operation(description = "Get the report for the cdio in the corresponding semester")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CDIOReport> getCDIOReport(@PathVariable Float cdioNumber, @PathVariable Integer semester){
        CDIOReport report = reportService.getCDIOReportByNumberAndSemester(cdioNumber, semester);
        return ResponseEntity.status(HttpStatus.OK).body(report);
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
