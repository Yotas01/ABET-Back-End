package edu.javeriana.abetbackend.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.CRUD.Services.Find.CourseFinder;
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

    public void saveCourse(Course course){
        try{
            Course foundCourse = finder.findCourseByNumber(course.getNumber());
        }catch (NotFound exception){
            repository.save(course);
            return;
        }
        throw new AlreadyExists("The course with number " + course.getNumber() + " already exists");
    }

    public Course updateCourse(Course course, Integer courseNumber){
        Course courseToUpdate = finder.findCourseByNumber(courseNumber);
        courseToUpdate.setNumber(course.getNumber());
        courseToUpdate.setName(course.getName());
        courseToUpdate.setRAEs(new ArrayList<>(course.getRAEs()));
        courseToUpdate.setCdioList(new ArrayList<>(course.getCdioList()));
        courseToUpdate.setSections(new ArrayList<>(course.getSections()));
        repository.save(courseToUpdate);
        return courseToUpdate;
    }

    public Course deleteCourse(Course course){
        Course courseToDelete = finder.findCourseById(course.getCourseId());
        repository.delete(courseToDelete);
        return courseToDelete;
    }
}
