package edu.javeriana.abetbackend.Reports.Controllers;

import edu.javeriana.abetbackend.Common.Constants;
import edu.javeriana.abetbackend.Entities.Views.OutcomeReport;
import edu.javeriana.abetbackend.Exceptions.*;
import edu.javeriana.abetbackend.Reports.Services.OutcomeReportService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/report")
public class OutcomeReportController {

    @Autowired
    private OutcomeReportService reportService;

    @GetMapping("/outcome/{outcomeId}/semester/{semester}")
    @Operation(description = "Get the report for the outcome with outcome id and the corresponding semester")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<OutcomeReport> getOutcomeReportByIdAndSemester(@PathVariable Integer outcomeId,
                                                                         @PathVariable Integer semester){
        OutcomeReport report = reportService.getOutcomeReportByIdAndSemester(outcomeId, semester);
        return ResponseEntity.status(HttpStatus.OK).body(report);
    }

    @ExceptionHandler({DoesNotContain.class, Inconsistent.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ABETSystemException badRequestError(Exception e){ return  new ABETSystemException(e.getMessage());}

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ABETSystemException notFoundError(Exception e){ return  new ABETSystemException(e.getMessage());}

    @ExceptionHandler({AlreadyContains.class, AlreadyExists.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ABETSystemException conflictError(Exception e){ return  new ABETSystemException(e.getMessage());}
}
