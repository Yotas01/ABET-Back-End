package edu.javeriana.abetbackend.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseFinder {

    @Autowired
    private CourseRepository repository;

    public  Course findCourseById(Long courseId){
        Optional<Course> optionalCourse = repository.findById(courseId);
        if(optionalCourse.isEmpty())
            throw new NotFound("The course with the id " + courseId + " was not found");
        return optionalCourse.get();
    }

    public Course findCourseByNumber(Integer courseId){
        Optional<Course> optionalCourse = repository.findCourseByNumber(courseId);
        if(optionalCourse.isEmpty())
            throw new NotFound("The course with the number " + courseId + " was not found");
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
