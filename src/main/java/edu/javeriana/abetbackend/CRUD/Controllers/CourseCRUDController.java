package edu.javeriana.abetbackend.CRUD.Controllers;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.CourseCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.ResponseEntities.ResponseCourse;
import edu.javeriana.abetbackend.Exceptions.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( value = "/admin")
public class CourseCRUDController {

    @Autowired
    private CourseCRUD courseCRUDService;
    @Autowired
    private CourseFinder courseFinder;

    @Operation(summary = "Create a new Course")
    @PostMapping("/course")
    public ResponseEntity<ResponseCourse> addCourse(@RequestBody Course course){
        courseCRUDService.saveCourse(course);
        ResponseCourse responseCourse = new ResponseCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCourse);
    }

    @Operation(summary = "Find the course with courseNumber")
    @GetMapping("/course/{courseNumber}")
    public ResponseEntity<ResponseCourse> findCourse(@PathVariable(value = "courseNumber") Integer number){
        Course course = courseFinder.findCourseByNumber(number);
        ResponseCourse responseCourse = new ResponseCourse(course);
        return ResponseEntity.status(HttpStatus.OK).body(responseCourse);
    }

    @Operation(summary = "Get all the courses")
    @GetMapping("/course")
    public ResponseEntity<List<ResponseCourse>> getAllCourses(){
        List<Course> courses = courseFinder.getAllCourses();
        List<ResponseCourse> responseCourses = new ArrayList<>();
        courses.forEach(course -> responseCourses.add(new ResponseCourse(course)));
        return ResponseEntity.status(HttpStatus.OK).body(responseCourses);
    }

    @Operation(summary = "Update a course that matches the course's id")
    @PutMapping("/course")
    public ResponseEntity<ResponseCourse> updateCourse(@RequestBody Course course){
        Course updatedCourse = courseCRUDService.updateCourse(course);
        ResponseCourse responseCourse = new ResponseCourse(updatedCourse);
        return ResponseEntity.status(HttpStatus.OK).body(responseCourse);
    }

    @Operation(summary = "Delete the course that matches the course number")
    @DeleteMapping("/course/{courseNumber}")
    public ResponseEntity<ResponseCourse> deleteCourseByNumber(@PathVariable(value = "courseNumber") Integer number){
        Course courseToDelete = courseFinder.findCourseByNumber(number);
        Course deletedCourse = courseCRUDService.deleteCourse(courseToDelete);
        ResponseCourse responseCourse = new ResponseCourse(deletedCourse);
        return ResponseEntity.status(HttpStatus.OK).body(responseCourse);
    }

    @ExceptionHandler({CourseNotFoundById.class, CourseNotFoundByName.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundError(Exception exception){
        return exception.getMessage();
    }

    @ExceptionHandler(CourseAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String alreadyExistsError(Exception exception){
        return exception.getMessage();
    }
}
