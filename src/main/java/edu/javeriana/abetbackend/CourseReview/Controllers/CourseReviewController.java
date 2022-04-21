package edu.javeriana.abetbackend.CourseReview.Controllers;

import edu.javeriana.abetbackend.CourseReview.Services.CRUD.CourseReviewService;
import edu.javeriana.abetbackend.Entities.CourseReview;
import edu.javeriana.abetbackend.Entities.SectionReview;
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
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CourseReview> getCourseForReview(@PathVariable Integer courseNumber,
                                                           @PathVariable Integer sectionNumber,
                                                           @PathVariable Integer semester){
        CourseReview courseReview = courseReviewService.getCourseForReview(courseNumber,sectionNumber,semester);
        return ResponseEntity.status(HttpStatus.OK).body(courseReview);
    }

    @Operation
    @PostMapping("/course/{courseNumber}/section/{sectionNumber}/semester/{semester}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity reviewCourseSection(@PathVariable Integer courseNumber,
                                              @PathVariable Integer sectionNumber,
                                              @PathVariable Integer semester,
                                              @RequestBody SectionReview sectionReview){
        courseReviewService.reviewCourseSection(courseNumber, sectionNumber, semester, sectionReview);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
