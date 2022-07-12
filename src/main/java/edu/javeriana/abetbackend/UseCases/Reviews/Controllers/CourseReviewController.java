package edu.javeriana.abetbackend.UseCases.Reviews.Controllers;

import edu.javeriana.abetbackend.Common.Constants;
import edu.javeriana.abetbackend.UseCases.Reviews.Services.CRUD.CourseReviewService;
import edu.javeriana.abetbackend.Entities.DTOs.CourseReview;
import edu.javeriana.abetbackend.Entities.DTOs.SectionReview;
import edu.javeriana.abetbackend.Exceptions.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course-review")
public class CourseReviewController {

    @Autowired
    private CourseReviewService courseReviewService;

    @Operation(description = "Get a course and it's section for review")
    @GetMapping("/course/{courseNumber}/section/{sectionNumber}/semester/{semester}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<CourseReview> getCourseForReview(@PathVariable Integer courseNumber,
                                                           @PathVariable Integer sectionNumber,
                                                           @PathVariable Integer semester){
        CourseReview courseReview = courseReviewService.getCourseForReview(courseNumber,sectionNumber,semester);
        return ResponseEntity.status(HttpStatus.OK).body(courseReview);
    }
    @Operation(description = "Get a previous section review")
    @GetMapping("/section-review/course/{courseNumber}/section/{sectionNumber}/semester/{semester}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<SectionReview> getSectionReview(@PathVariable Integer courseNumber,
                                                          @PathVariable Integer sectionNumber,
                                                          @PathVariable Integer semester){
        SectionReview sectionReview = courseReviewService.getSectionReview(courseNumber,sectionNumber,semester);
        return ResponseEntity.status(HttpStatus.OK).body(sectionReview);
    }

    @Operation
    @PostMapping("/course/{courseNumber}/section/{sectionNumber}/semester/{semester}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity reviewCourseSection(@PathVariable Integer courseNumber,
                                              @PathVariable Integer sectionNumber,
                                              @PathVariable Integer semester,
                                              @RequestBody SectionReview sectionReview){
        courseReviewService.reviewCourseSection(courseNumber, sectionNumber, semester, sectionReview);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Operation
    @PutMapping("/course/{courseNumber}/section/{sectionNumber}/semester/{semester}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity updateSectionReview(@PathVariable Integer courseNumber,
                                              @PathVariable Integer sectionNumber,
                                              @PathVariable Integer semester,
                                              @RequestBody SectionReview sectionReview){
        courseReviewService.updateSectionReview(courseNumber, sectionNumber, semester, sectionReview);
        return ResponseEntity.status(HttpStatus.OK).body(null);
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
