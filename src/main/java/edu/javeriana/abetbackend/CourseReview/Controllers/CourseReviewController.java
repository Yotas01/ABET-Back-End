package edu.javeriana.abetbackend.CourseReview.Controllers;

import edu.javeriana.abetbackend.CourseReview.Services.CRUD.CourseReviewService;
import edu.javeriana.abetbackend.Entities.CourseReview;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course-review")
public class CourseReviewController {

    @Autowired
    private CourseReviewService courseReviewService;

    @Operation(description = "Get a course and it's section for review")
    @GetMapping("/course/{courseNumber}/section/{sectionNumber}/semester/{semester}")
    public ResponseEntity<CourseReview> getCourseForReview(@PathVariable Integer courseNumber,
                                                           @PathVariable Integer sectionNumber,
                                                           @PathVariable Integer semester){
        CourseReview courseReview = courseReviewService.getCourseForReview(courseNumber,sectionNumber,semester);
        return ResponseEntity.status(HttpStatus.OK).body(courseReview);
    }
}
