package edu.javeriana.abetbackend.UseCases.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.CourseRepository;
import edu.javeriana.abetbackend.Repositories.Course_has_CdioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseFinder {

    @Autowired
    private CourseRepository repository;
    @Autowired
    private Course_has_CdioRepository course_has_cdioRepository;

    public  Course findCourseById(Long courseId){
        Optional<Course> optionalCourse = repository.findById(courseId);
        if(optionalCourse.isEmpty())
            throw new NotFound("The course with the id " + courseId + " was not found");
        return optionalCourse.get();
    }

    public Course findCourseByNumber(Integer courseNumber){
        Optional<Course> optionalCourse = repository.findCourseByNumber(courseNumber);
        if(optionalCourse.isEmpty())
            throw new NotFound("The course with the number " + courseNumber + " was not found");
        var optionalCDIOS = course_has_cdioRepository.findAllByCourse(optionalCourse.get());
        optionalCDIOS.ifPresent(course_has_cdios -> optionalCourse.get().setCdioList(course_has_cdios));
        return optionalCourse.get();
    }

    public Course findCourseByName(String courseName){
        Optional<Course> optionalCourse = repository.findCourseByName(courseName);
        if(optionalCourse.isEmpty())
            throw new NotFound("The course with the name " + courseName + " was not found");
        return optionalCourse.get();
    }

    public List<Course> getAllCourses(){
        return (List<Course>) repository.findAll();
    }
}
