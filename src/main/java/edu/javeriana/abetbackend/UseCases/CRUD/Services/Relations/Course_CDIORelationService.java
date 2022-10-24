package edu.javeriana.abetbackend.UseCases.CRUD.Services.Relations;

import edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD.CDIOCRUD;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD.CourseCRUD;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Course_has_CDIO;
import edu.javeriana.abetbackend.Entities.Ids.Course_has_CdioId;
import edu.javeriana.abetbackend.Exceptions.AlreadyContains;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.Course_has_CdioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Course_CDIORelationService {

    @Autowired
    private CDIOCRUD cdioService;
    @Autowired
    private CDIOFinder cdioFinder;
    @Autowired
    private CourseCRUD courseService;
    @Autowired
    private CourseFinder courseFinder;
    @Autowired
    private Course_has_CdioRepository course_has_cdioRepository;

    public Course_has_CDIO getCourseHasCDIO(Integer courseNumber, Float cdioNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        CDIO cdio = cdioFinder.findCDIOById(cdioNumber);
        Course_has_CdioId id = new Course_has_CdioId(course.getCourseId(), cdioNumber);
        Optional<Course_has_CDIO> courseHasCdio = course_has_cdioRepository.findById(id);
        if(courseHasCdio.isEmpty())
            throw new NotFound("The course " + courseNumber + " does not have a relation " +
                    "with the CDIO " + cdioNumber);
        return courseHasCdio.get();
    }

    public Course addCDIOToCourse(Integer courseNumber, Float cdioNumber, Integer value){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        CDIO cdio = cdioFinder.findCDIOById(cdioNumber);
        if(course.getListOfCDIO().contains(cdio))
            throw new AlreadyContains("The course " + course.getName() + " already contains the cdio "
                    + cdioNumber);
        course.addCDIO(cdio, value);
        courseService.updateCourse(course, courseNumber);
        Course_has_CDIO course_has_cdio = new Course_has_CDIO(new Course_has_CdioId(course.getCourseId(),cdioNumber),course, cdio, value);
        course_has_cdioRepository.save(course_has_cdio);
        return course;
    }

    public Course_has_CDIO updateCDIOHasCourse(Integer courseNumber, Float cdioNumber, Integer value){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        CDIO cdio = cdioFinder.findCDIOById(cdioNumber);
        if(!course.getListOfCDIO().contains(cdio))
            throw new DoesNotContain("The course " + course.getName() + " does not contain the cdio "
                    + cdioNumber);
        Course_has_CdioId id = new Course_has_CdioId(course.getCourseId(),cdioNumber);
        Optional<Course_has_CDIO> courseHasCdio = course_has_cdioRepository.findById(id);
        if (courseHasCdio.isEmpty())
            throw new NotFound("The course relation to the cdio " + courseHasCdio +
                    " was not found");
        courseHasCdio.get().setBloomValue(value);
        course_has_cdioRepository.save(courseHasCdio.get());
        return courseHasCdio.get();
    }

    public Course deleteCDIOFromCourse(Integer courseNumber, Float cdioNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        CDIO cdio = cdioFinder.findCDIOById(cdioNumber);
        if(!course.getListOfCDIO().contains(cdio))
            throw new DoesNotContain("The course " + course.getName() + " does not contain the cdio "
                    + cdioNumber);
        Course_has_CdioId id = new Course_has_CdioId(course.getCourseId(), cdioNumber);
        Optional<Course_has_CDIO> courseHasCdio = course_has_cdioRepository.findById(id);
        if (courseHasCdio.isEmpty())
            throw new NotFound("The course relation to the cdio " + courseHasCdio +
                    " was not found");
        course.removeCDIO(cdio);
        course_has_cdioRepository.delete(courseHasCdio.get());
        return course;
    }

    public List<Course_has_CDIO> getAllByCourse(Integer courseNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Optional<List<Course_has_CDIO>> courseHasCdios = course_has_cdioRepository.findAllByCourse(course);
        if(courseHasCdios.isEmpty())
            throw new NotFound("There are no CDIOs associated with the course " + courseNumber);
        return courseHasCdios.get();
    }
}
