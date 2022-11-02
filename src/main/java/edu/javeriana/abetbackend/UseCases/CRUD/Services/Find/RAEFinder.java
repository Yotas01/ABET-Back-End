package edu.javeriana.abetbackend.UseCases.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.RAERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RAEFinder {

    @Autowired
    private RAERepository raeRepository;
    @Autowired
    private CourseFinder courseFinder;

    public RAE findRAEById(Long raeId){
        Optional<RAE> optionalRAE = raeRepository.findById(raeId);
        if(optionalRAE.isEmpty())
            throw new NotFound("The RAE with id " + raeId + " was not found");
        return optionalRAE.get();
    }

    public RAE findRAEByCourseAndRaeId(Integer courseNumber, Long raeId){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Optional<RAE> optionalRAE = raeRepository.findById(raeId);
        if(optionalRAE.isEmpty())
            throw new NotFound("The RAE with id " + raeId + " was not found");
        if(!optionalRAE.get().getCourse().equals(course))
            throw new DoesNotContain("The course " + courseNumber + ":" + course.getName() +
                    " does not contain the RAE with id " + raeId);
        return optionalRAE.get();
    }

    public List<RAE> findRAEsFromCourseNumber(Integer courseNumber, Integer semester){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Optional<List<RAE>> optionalRAES = raeRepository.findAllByCourseAndSemester(course, semester);
        if (optionalRAES.isEmpty())
            throw new NotFound("There where no RAEs found from the course " +
                    courseNumber + ":" + course.getName());
        return optionalRAES.get();
    }
}
