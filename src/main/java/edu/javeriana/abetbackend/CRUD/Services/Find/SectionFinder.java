package edu.javeriana.abetbackend.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Exceptions.CourseNotFoundById;
import edu.javeriana.abetbackend.Exceptions.SectionNotFound;
import edu.javeriana.abetbackend.Exceptions.SectionNotFoundByProfessor;
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
    private CourseRepository courseRepository;

    public Section findSectionById(Long sectionId){
        Optional<Section> optionalSection = sectionRepository.findById(sectionId);
        if(optionalSection.isEmpty())
            throw new SectionNotFound("The section with id " + sectionId + " was not found");
        return optionalSection.get();
    }

    public Section findSectionByNumber(Integer sectionNumber){
        Optional<Section> optionalSection = sectionRepository.findSectionByNumber(sectionNumber);
        if(optionalSection.isEmpty())
            throw new SectionNotFound("The section with number " + sectionNumber + " was not found");
        return optionalSection.get();
    }

    public List<Section> findSectionsFromCourseNumber(Integer courseNumber){
        Optional<Course> optionalCourse = courseRepository.findCourseByNumber(courseNumber);
        if(optionalCourse.isEmpty())
            throw new CourseNotFoundById("The course with the number " + courseNumber + " was not found");
        return optionalCourse.get().getSections();
    }

    public List<Section> getAllSections(){
        return (List<Section>) sectionRepository.findAll();
    }

    public List<Section> findByProfessor(String professor){
        Optional<List<Section>> optionalSections = sectionRepository.findSectionByProfessor(professor);
        if(optionalSections.isEmpty())
            throw new SectionNotFoundByProfessor("The section with the professor " + professor + " was not found");
        return optionalSections.get();
    }
}
