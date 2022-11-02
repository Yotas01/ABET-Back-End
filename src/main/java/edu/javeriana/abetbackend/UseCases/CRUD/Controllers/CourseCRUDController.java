package edu.javeriana.abetbackend.UseCases.CRUD.Controllers;

import edu.javeriana.abetbackend.UseCases.BaseController;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD.CourseCRUD;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.DTOs.CourseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( value = "/admin")
@CrossOrigin(origins = "*")
public class CourseCRUDController extends BaseController {

    @Autowired
    private CourseCRUD courseCRUDService;
    @Autowired
    private CourseFinder courseFinder;


    @ApiOperation(value = "Create a new Course")
    @PostMapping("/course")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO course){
        Course createdCourse = courseCRUDService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CourseDTO(createdCourse));
    }

    @ApiOperation(value = "Find the course that corresponds to the course number")
    @GetMapping("/course/{courseNumber}")
    public ResponseEntity<CourseDTO> findCourse(@ApiParam(name = "courseNumber", required = true)
                                                    @PathVariable Integer courseNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new CourseDTO(course));
    }

    @ApiOperation(value = "Get all the courses")
    @GetMapping("/course")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        List<Course> courses = courseFinder.getAllCourses();
        List<CourseDTO> courseDTOs = new ArrayList<>();
        courses.forEach(course -> courseDTOs.add(new CourseDTO(course)));
        return ResponseEntity.status(HttpStatus.OK).body(courseDTOs);
    }

    @ApiOperation(value = "Update a course that matches the course's number")
    @PutMapping("/course/{courseNumber}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO course,
                                                  @ApiParam(name = "courseNumber", required = true)
                                                  @PathVariable Integer courseNumber){
        Course updatedCourse = courseCRUDService.updateCourse(course, courseNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new CourseDTO(updatedCourse));
    }

    @ApiOperation(value = "Delete the course that matches the course number")
    @DeleteMapping("/course/{courseNumber}")
    public ResponseEntity<CourseDTO> deleteCourseByNumber(@ApiParam(name = "courseNumber", required = true)
                                                              @PathVariable Integer courseNumber){
        Course deletedCourse = courseCRUDService.deleteCourse(courseNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new CourseDTO(deletedCourse));
    }
}
