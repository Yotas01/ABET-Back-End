package edu.javeriana.abetbackend.UseCases.Reports.Controllers;

import edu.javeriana.abetbackend.Common.Constants;
import edu.javeriana.abetbackend.Entities.DTOs.CDIOReportDTO;
import edu.javeriana.abetbackend.Entities.Views.CDIOReport;
import edu.javeriana.abetbackend.Exceptions.*;
import edu.javeriana.abetbackend.UseCases.Reports.Services.CDIOReportService;
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
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<CDIOReportDTO> getCDIOReport(@PathVariable Float cdioNumber, @PathVariable Integer semester){
        CDIOReportDTO report = reportService.getCDIOReportByNumberAndSemester(cdioNumber, semester);
        return ResponseEntity.status(HttpStatus.OK).body(report);
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
