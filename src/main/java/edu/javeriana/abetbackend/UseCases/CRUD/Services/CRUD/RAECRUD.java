package edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.RAEFinder;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import edu.javeriana.abetbackend.Repositories.CourseRepository;
import edu.javeriana.abetbackend.Repositories.RAERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RAECRUD {

    @Autowired
    private RAERepository raeRepository;
    @Autowired
    private RAEFinder raeFinder;
    @Autowired
    private CourseFinder courseFinder;
    @Autowired
    private CourseRepository courseRepository;

    public void saveRAE(RAE rae, Integer courseNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        rae.setCourse(course);
        course.addRAE(rae);
        raeRepository.save(rae);
        courseRepository.save(course);
    }

    public void saveRAE(RAE rae){
        raeRepository.save(rae);
    }

    public void updateRAE(Integer courseNumber, Long raeId, String description){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        RAE raeToUpdate = raeFinder.findRAEById(raeId);
        if(!raeToUpdate.getCourse().equals(course))
            throw new DoesNotContain("The course " + course.getName() + " does not contain the rae "
                    + raeId);
        raeToUpdate.setDescription(description);
        raeRepository.save(raeToUpdate);
    }

    public void deleteRAE(Integer courseNumber, Long raeId){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        RAE raeToDelete = raeFinder.findRAEById(raeId);
        if(!raeToDelete.getCourse().equals(course))
            throw new DoesNotContain("The course " + course.getName() + " does not contain the rae "
                    + raeId);
        raeRepository.delete(raeToDelete);
    }
}
