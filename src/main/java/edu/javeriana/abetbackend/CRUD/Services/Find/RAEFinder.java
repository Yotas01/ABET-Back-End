package edu.javeriana.abetbackend.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Exceptions.NotFound.CourseNotFoundById;
import edu.javeriana.abetbackend.Exceptions.NotFound.RAENotFoundByCourse;
import edu.javeriana.abetbackend.Exceptions.NotFound.RAENotFoundByCourseAndDescription;
import edu.javeriana.abetbackend.Exceptions.NotFound.RAENotFoundById;
import edu.javeriana.abetbackend.Repositories.CourseRepository;
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
    private CourseRepository courseRepository;

    public RAE findRAEById(Long raeId){
        Optional<RAE> optionalRAE = raeRepository.findById(raeId);
        if(optionalRAE.isEmpty())
            throw new RAENotFoundById("The RAE with id " + raeId + " was not found");
        return optionalRAE.get();
    }

    public List<RAE> findRAEsFromCourseNumber(Integer courseNumber){
        Optional<Course> optionalCourse = courseRepository.findCourseByNumber(courseNumber);
        if (optionalCourse.isEmpty())
            throw new CourseNotFoundById("The course with the number " + courseNumber + " was not found");
        Optional<List<RAE>> optionalRAES = raeRepository.findAllByCourse(optionalCourse.get());
        if (optionalRAES.isEmpty())
            throw new RAENotFoundByCourse("There where no RAEs found from the course " +
                    optionalCourse.get().getNumber() + ":" + optionalCourse.get().getName());
        return optionalRAES.get();
    }

    public RAE findRAEByCourseAndDescription(Integer courseNumber, String description){
        List<RAE> courseRaes = findRAEsFromCourseNumber(courseNumber);
        Optional<RAE> rae = courseRaes.stream().
                filter(r -> r.getDescription().equals(description))
                .findAny();
        if (rae.isEmpty())
            throw new RAENotFoundByCourseAndDescription("The rae with description " + description + " and course " +
                    courseNumber + " was not found");
        return rae.get();
    }
}
