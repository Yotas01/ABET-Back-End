package edu.javeriana.abetbackend.UseCases.Reports.Controllers;

import edu.javeriana.abetbackend.Entities.DTOs.*;
import edu.javeriana.abetbackend.Entities.SemesterReport;
import edu.javeriana.abetbackend.Exceptions.*;
import edu.javeriana.abetbackend.UseCases.BaseController;
import edu.javeriana.abetbackend.UseCases.Reports.Services.CDIOReportService;
import edu.javeriana.abetbackend.UseCases.Reports.Services.CourseReportService;
import edu.javeriana.abetbackend.UseCases.Reports.Services.OutcomeReportService;
import edu.javeriana.abetbackend.UseCases.Reports.Services.SemesterReportService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/report")
public class ReportController extends BaseController {

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
    public ResponseEntity<CourseReportDTO> getCourseReport(@PathVariable Integer courseNumber,
                                                           @PathVariable Integer semester){

        CourseReportDTO courseReport = courseReportService.getCourseReport(courseNumber, semester);
        return ResponseEntity.status(HttpStatus.OK).body(courseReport);
    }

    @Operation(description = "Generate a report for a cdio in an specific semester")
    @GetMapping("/cdio/{cdioNumber}/semester/{semester}")
    public ResponseEntity<CDIOReportDTO> getCDIOReport(@PathVariable Float cdioNumber,
                                                       @PathVariable Integer semester){

        //TODO: Implement for semester
        CDIOReportDTO cdioReportDTO = cdioReportService.getCDIOReport(cdioNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cdioReportDTO);
    }

    @Operation(description = "Generate a report for an outcome in an specific semester")
    @GetMapping("/outcome/{outcomeNumber}/semester/{semester}")
    public ResponseEntity<OutcomeReportDTO> getOutcomeReport(@PathVariable Integer outcomeNumber,
                                                             @PathVariable Integer semester){

        //TODO: Implement for semester
        OutcomeReportDTO outcomeSummary = outcomeReportService.getOutcomeSummary(outcomeNumber);
        return ResponseEntity.status(HttpStatus.OK).body(outcomeSummary);
    }

    @Operation(description = "Save the Improvement actions and the semester performance of a semester")
    @PostMapping("/semester-report/outcome/{outcomeId}")
    public ResponseEntity saveSemesterReport(@ApiParam(name = "outcomeId", required = true)
                                                 @RequestParam Integer outcomeId,
                                             @RequestBody SemesterReport semesterReport){
        semesterReportService.saveSemesterReport(semesterReport, outcomeId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Operation(description = "Get the Improvement actions and the semester performance of a semester")
    @GetMapping("/semester-report/{semester}/outcome/{outcomeId}")
    public ResponseEntity<SemesterReport> getSemesterReport(@PathVariable Integer semester,
                                                            @ApiParam(name = "outcomeId", required = true)
                                                            @RequestParam Integer outcomeId){
        SemesterReport report = semesterReportService.getSemesterReport(semester, outcomeId);
        return ResponseEntity.status(HttpStatus.OK).body(report);
    }
}
