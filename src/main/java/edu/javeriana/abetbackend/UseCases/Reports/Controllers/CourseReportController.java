package edu.javeriana.abetbackend.UseCases.Reports.Controllers;

import edu.javeriana.abetbackend.Common.Constants;
import edu.javeriana.abetbackend.Entities.DTOs.CourseReportDTO;
import edu.javeriana.abetbackend.UseCases.Reports.Services.CourseReportService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/report/course")
public class CourseReportController {

    @Autowired
    private CourseReportService courseReportService;

    @Operation(description = "Generate a report for a course in an specific semester")
    @GetMapping("/{courseNumber}/semester/{semester}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<CourseReportDTO> getCourseReport(@PathVariable Integer courseNumber,
                                                           @PathVariable Integer semester){

        CourseReportDTO courseReport = courseReportService.getCourseReport(courseNumber, semester);
        return ResponseEntity.status(HttpStatus.OK).body(courseReport);
    }

}
