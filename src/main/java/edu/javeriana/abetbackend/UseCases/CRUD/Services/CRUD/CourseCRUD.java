package edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.Entities.DTOs.CourseDTO;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Exceptions.AlreadyExists;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseCRUD {

    @Autowired
    private CourseRepository repository;
    @Autowired
    private CourseFinder finder;

    public Course createCourse(CourseDTO dto){
        try{
            Course foundCourse = finder.findCourseByNumber(dto.getCourseId());
        }catch (NotFound exception){
            Course course = new Course(dto.getCourseId(), dto.getName());
            repository.save(course);
            return course;
        }
        throw new AlreadyExists("The course with number " + dto.getCourseId() + " already exists");
    }

    public Course updateCourse(CourseDTO course, Integer courseNumber){
        Course courseToUpdate = finder.findCourseByNumber(courseNumber);
        courseToUpdate.setCourseId(course.getCourseId());
        courseToUpdate.setName(course.getName());
        repository.save(courseToUpdate);
        return courseToUpdate;
    }

    public Course deleteCourse(Integer courseNumber){
        Course courseToDelete = finder.findCourseByNumber(courseNumber);
        repository.delete(courseToDelete);
        return courseToDelete;
    }
}
