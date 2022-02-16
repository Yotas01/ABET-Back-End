package edu.javeriana.abetbackend.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Exceptions.CourseAlreadyExists;
import edu.javeriana.abetbackend.Exceptions.CourseNotFoundById;
import edu.javeriana.abetbackend.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseCRUD {

    @Autowired
    private CourseRepository repository;
    @Autowired
    private CourseFinder finder;

    public void saveCourse(Course course){
        try{
            Course foundCourse = finder.findCourseByNumber(course.getNumber());
        }catch (CourseNotFoundById exception){
            repository.save(course);
            return;
        }
        throw new CourseAlreadyExists("The course with number " + course.getNumber() + " already exists");
    }

    public Course updateCourse(Course course){
        Course courseToUpdate = finder.findCourseById(course.getCourseId());
        courseToUpdate.setNumber(course.getNumber());
        courseToUpdate.setName(course.getName());
        courseToUpdate.setRAEs(new ArrayList<>(course.getRAEs()));
        courseToUpdate.setCdioList(new ArrayList<>(course.getCdioList()));
        courseToUpdate.setSections(new ArrayList<>(course.getSections()));
        repository.save(courseToUpdate);
        return course;
    }

    public Course deleteCourse(Course course){
        Course courseToDelete = finder.findCourseById(course.getCourseId());
        repository.delete(courseToDelete);
        return courseToDelete;
    }
}
