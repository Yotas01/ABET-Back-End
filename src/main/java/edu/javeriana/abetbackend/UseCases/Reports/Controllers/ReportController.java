package edu.javeriana.abetbackend.UseCases.Reports.Controllers;

import edu.javeriana.abetbackend.Common.Constants;
import edu.javeriana.abetbackend.Entities.DTOs.*;
import edu.javeriana.abetbackend.Entities.SemesterReport;
import edu.javeriana.abetbackend.Entities.Views.OutcomeSummary;
import edu.javeriana.abetbackend.Exceptions.*;
import edu.javeriana.abetbackend.UseCases.Reports.Services.CDIOReportService;
import edu.javeriana.abetbackend.UseCases.Reports.Services.CourseReportService;
import edu.javeriana.abetbackend.UseCases.Reports.Services.OutcomeReportService;
import edu.javeriana.abetbackend.UseCases.Reports.Services.SemesterReportService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/report")
public class ReportController {

    @Autowired
    private CourseReportService courseReportService;
    @Autowired
    private CDIOReportService cdioReportService;
    @Autowired
    private OutcomeReportService outcomeReportService;
    @Autowired
    private SemesterReportService semesterReportService;

    @Operation(description = "Generate a report for a course in an specific semester")
    @GetMapping("/course/{courseNumber}/semester/{semester}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<CourseReportDTO> getCourseReport(@PathVariable Integer courseNumber,
                                                           @PathVariable Integer semester){

        CourseReportDTO courseReport = courseReportService.getCourseReport(courseNumber, semester);
        return ResponseEntity.status(HttpStatus.OK).body(courseReport);
    }

    @Operation(description = "Generate a report for a cdio in an specific semester")
    @GetMapping("/cdio/{cdioNumber}/semester/{semester}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<CDIOReportDTO> getCDIOReport(@PathVariable Float cdioNumber,
                                                       @PathVariable Integer semester){

        //TODO: Implement for semester
        CDIOReportDTO cdioReportDTO = cdioReportService.getCDIOReport(cdioNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cdioReportDTO);
    }

    @Operation(description = "Generate a report for an outcome in an specific semester")
    @GetMapping("/outcome/{outcomeNumber}/semester/{semester}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<OutcomeReportDTO> getOutcomeReport(@PathVariable Integer outcomeNumber,
                                                             @PathVariable Integer semester){

        //TODO: Implement for semester
        OutcomeReportDTO outcomeSummary = outcomeReportService.getOutcomeSummary(outcomeNumber);
        return ResponseEntity.status(HttpStatus.OK).body(outcomeSummary);
    }

    @Operation(description = "Save the Improvement actions and the semester performance of a semester")
    @PostMapping("/semester-report")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity saveSemesterReport(@RequestBody SemesterReport semesterReport){
        semesterReportService.saveSemesterReport(semesterReport);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Operation(description = "Get the Improvement actions and the semester performance of a semester")
    @GetMapping("/semester-report/{semester}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<SemesterReport> getSemesterReport(@PathVariable Integer semester){
        SemesterReport report = semesterReportService.getSemesterReport(semester);
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
