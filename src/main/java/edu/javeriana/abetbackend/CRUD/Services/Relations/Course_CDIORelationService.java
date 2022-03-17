package edu.javeriana.abetbackend.CRUD.Services.Relations;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.CDIOCRUD;
import edu.javeriana.abetbackend.CRUD.Services.CRUD.CourseCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Course_has_CDIO;
import edu.javeriana.abetbackend.Entities.Ids.Course_has_CdioId;
import edu.javeriana.abetbackend.Exceptions.AlreadyContains.CourseAlreadyContainsCDIO;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain.CourseDoesNotContainCDIO;
import edu.javeriana.abetbackend.Exceptions.NotFound.CourseHasCDIONotFoundById;
import edu.javeriana.abetbackend.Exceptions.NotFound.RelationBetweenCourseAndCDIONotFound;
import edu.javeriana.abetbackend.Repositories.Course_has_CdioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        CDIO cdio = cdioFinder.findCDIOByNumber(cdioNumber);
        Course_has_CdioId id = new Course_has_CdioId(course,cdio);
        Optional<Course_has_CDIO> courseHasCdio = course_has_cdioRepository.findById(id);
        if(courseHasCdio.isEmpty())
            throw new RelationBetweenCourseAndCDIONotFound("The course " + courseNumber + " does not have a relation " +
                    "with the CDIO " + cdioNumber);
        return courseHasCdio.get();
    }

    public Course_has_CDIO addCDIOToCourse(Integer courseNumber, Float cdioNumber, Integer value){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        CDIO cdio = cdioFinder.findCDIOByNumber(cdioNumber);
        if(course.getCdioList().contains(cdio))
            throw new CourseAlreadyContainsCDIO("The course " + course.getName() + " already contains the cdio "
                    + cdioNumber);
        course.addCDIo(cdio);
        cdio.addCourse(course);
        cdioService.updateCDIO(cdio);
        courseService.updateCourse(course, courseNumber);
        Course_has_CDIO course_has_cdio = new Course_has_CDIO(new Course_has_CdioId(course,cdio),value);
        course_has_cdioRepository.save(course_has_cdio);
        return course_has_cdio;
    }

    public Course_has_CDIO deleteCDIOFromCourse(Integer courseNumber, Float cdioNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        CDIO cdio = cdioFinder.findCDIOByNumber(cdioNumber);
        if(!course.getCdioList().contains(cdio))
            throw new CourseDoesNotContainCDIO("The course " + course.getName() + " does not contain the cdio "
                    + cdioNumber);
        Course_has_CdioId id = new Course_has_CdioId(course,cdio);
        Optional<Course_has_CDIO> courseHasCdio = course_has_cdioRepository.findById(id);
        if (courseHasCdio.isEmpty())
            throw new CourseHasCDIONotFoundById("The course relation to the cdio " + courseHasCdio +
                    " was not found");
        course_has_cdioRepository.delete(courseHasCdio.get());
        course.removeCDIo(cdio);
        cdio.removeCourse(course);
        cdioService.updateCDIO(cdio);
        courseService.updateCourse(course, courseNumber);
        return new Course_has_CDIO(id,courseHasCdio.get().getValue());
    }
}
