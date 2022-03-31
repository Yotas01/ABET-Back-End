package edu.javeriana.abetbackend.CRUD.Controllers;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.CourseCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.DTOs.CourseDTO;
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
    public ResponseEntity<CourseDTO> addCourse(@RequestBody Course course){
        courseCRUDService.saveCourse(course);
        CourseDTO courseDTO = new CourseDTO(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseDTO);
    }

    @Operation(summary = "Find the course with courseNumber")
    @GetMapping("/course/{courseNumber}")
    public ResponseEntity<CourseDTO> findCourse(@PathVariable(value = "courseNumber") Integer number){
        Course course = courseFinder.findCourseByNumber(number);
        CourseDTO courseDTO = new CourseDTO(course);
        return ResponseEntity.status(HttpStatus.OK).body(courseDTO);
    }

    @Operation(summary = "Get all the courses")
    @GetMapping("/course")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        List<Course> courses = courseFinder.getAllCourses();
        List<CourseDTO> courseDTOs = new ArrayList<>();
        courses.forEach(course -> courseDTOs.add(new CourseDTO(course)));
        return ResponseEntity.status(HttpStatus.OK).body(courseDTOs);
    }

    @Operation(summary = "Update a course that matches the course's id")
    @PutMapping("/course/{courseNumber}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody Course course,
                                                  @PathVariable(name = "courseNumber")Integer courseNumber){
        Course updatedCourse = courseCRUDService.updateCourse(course, courseNumber);
        CourseDTO courseDTO = new CourseDTO(updatedCourse);
        return ResponseEntity.status(HttpStatus.OK).body(courseDTO);
    }

    @Operation(summary = "Delete the course that matches the course number")
    @DeleteMapping("/course/{courseNumber}")
    public ResponseEntity<CourseDTO> deleteCourseByNumber(@PathVariable(value = "courseNumber") Integer number){
        Course courseToDelete = courseFinder.findCourseByNumber(number);
        Course deletedCourse = courseCRUDService.deleteCourse(courseToDelete);
        CourseDTO courseDTO = new CourseDTO(deletedCourse);
        return ResponseEntity.status(HttpStatus.OK).body(courseDTO);
    }

    @ExceptionHandler({DoesNotContain.class, Inconsistent.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badRequestError(Exception e){ return  e.getMessage();}

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundError(Exception e){ return  e.getMessage();}

    @ExceptionHandler({AlreadyContains.class, AlreadyExists.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictError(Exception e){ return  e.getMessage();}
}
