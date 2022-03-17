package edu.javeriana.abetbackend.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Exceptions.NotFound.CourseNotFoundById;
import edu.javeriana.abetbackend.Exceptions.NotFound.SectionNotFound;
import edu.javeriana.abetbackend.Exceptions.NotFound.SectionNotFoundByProfessor;
import edu.javeriana.abetbackend.Repositories.CourseRepository;
import edu.javeriana.abetbackend.Repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionFinder {

    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private CourseFinder courseFinder;

    public Section findSectionById(Long sectionId){
        Optional<Section> optionalSection = sectionRepository.findById(sectionId);
        if(optionalSection.isEmpty())
            throw new SectionNotFound("The section with id " + sectionId + " was not found");
        return optionalSection.get();
    }

    public Section findSectionByNumber(Integer courseNumber, Integer sectionNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Optional<Section> optionalSection = sectionRepository.findSectionByNumberAndCourse(sectionNumber, course);
        if(optionalSection.isEmpty())
            throw new SectionNotFound("The section with number " + sectionNumber + " for the course " + courseNumber
                    + " was not found");
        return optionalSection.get();
    }

    public List<Section> findSectionsFromCourseNumber(Integer courseNumber){
        Course optionalCourse = courseFinder.findCourseByNumber(courseNumber);
        return optionalCourse.getSections();
    }

    public List<Section> getAllSections(){
        return (List<Section>) sectionRepository.findAll();
    }

    public List<Section> findByProfessor(String professor){
        Optional<List<Section>> optionalSections = sectionRepository.findAllByProfessor(professor);
        if(optionalSections.isEmpty())
            throw new SectionNotFoundByProfessor("The section with the professor " + professor + " was not found");
        return optionalSections.get();
    }
}
