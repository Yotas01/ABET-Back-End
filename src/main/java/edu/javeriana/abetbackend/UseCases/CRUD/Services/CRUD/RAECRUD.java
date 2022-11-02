package edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.Entities.DTOs.RAEDTO;
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

    public RAE createRAE(RAEDTO raedto, Integer courseNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        RAE rae = new RAE(raedto.getRaeId(), raedto.getDescription(), raedto.getSemester(), course);
        course.addRAE(rae);
        raeRepository.save(rae);
        courseRepository.save(course);
        return rae;
    }

    public void saveRAE(RAE rae){
        raeRepository.save(rae);
    }

    public RAE updateRAE(Integer courseNumber, Long raeId, RAEDTO raedto){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        RAE raeToUpdate = raeFinder.findRAEById(raeId);
        if(!raeToUpdate.getCourse().equals(course))
            throw new DoesNotContain("The course " + course.getName() + " does not contain the rae "
                    + raeId);
        raeToUpdate.setDescription(raedto.getDescription());
        raeToUpdate.setSemester(raedto.getSemester());
        raeRepository.save(raeToUpdate);
        return raeToUpdate;
    }

    public RAE deleteRAE(Integer courseNumber, Long raeId){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        RAE raeToDelete = raeFinder.findRAEById(raeId);
        if(!raeToDelete.getCourse().equals(course))
            throw new DoesNotContain("The course " + course.getName() + " does not contain the rae "
                    + raeId);
        raeRepository.delete(raeToDelete);
        return raeToDelete;
    }
}
