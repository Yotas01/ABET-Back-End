package edu.javeriana.abetbackend.Reports.Controllers;

import edu.javeriana.abetbackend.Common.Constants;
import edu.javeriana.abetbackend.Entities.Views.CourseReport;
import edu.javeriana.abetbackend.Exceptions.*;
import edu.javeriana.abetbackend.Reports.Services.CourseReportService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/report")
public class CourseReportController {

    @Autowired
    private CourseReportService reportService;

    @GetMapping("/course/{courseNumber}/semester/{semester}")
    @Operation(description = "Get the report for the course with the course Id for the specified semester")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<CourseReport> getCourseReport(@PathVariable Integer courseNumber, @PathVariable Integer semester){
        CourseReport report = reportService.getCourseReportByIdAndSemester(courseNumber, semester);
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
