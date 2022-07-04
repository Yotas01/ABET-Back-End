package edu.javeriana.abetbackend.UseCases.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Exceptions.NotFound;
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
            throw new NotFound("The section with id " + sectionId + " was not found");
        return optionalSection.get();
    }

    public Section findSectionByNumber(Integer courseNumber, Integer sectionNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Optional<Section> optionalSection = sectionRepository.findSectionByNumberAndCourse(sectionNumber, course);
        if(optionalSection.isEmpty())
            throw new NotFound("The section with number " + sectionNumber + " for the course " + courseNumber
                    + " was not found");
        return optionalSection.get();
    }

    public Section findSectionByNumberAndSemester(Integer courseNumber, Integer sectionNumber, Integer semester){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Optional<Section> optionalSection = sectionRepository.findSectionByNumberAndCourseAndSemester(sectionNumber, course, semester);
        if(optionalSection.isEmpty())
            throw new NotFound("The section with number " + sectionNumber + " for the course " + courseNumber
                    + " and the semester " + semester + " was not found");
        return optionalSection.get();
    }

    public List<Section> findSectionsFromCourseNumberAndSemester(Integer courseNumber, Integer semester){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Optional<List<Section>> sections = sectionRepository.findSectionByCourseAndSemester(course, semester);
        if (sections.isEmpty() || sections.get().isEmpty())
            throw new NotFound("There were no sections found for the course " + courseNumber
                    + " and the semester " + semester);
        return sections.get();
    }

    public List<Section> findByProfessor(String professor){
        Optional<List<Section>> optionalSections = sectionRepository.findAllByProfessor(professor);
        if(optionalSections.isEmpty())
            throw new NotFound("The section with the professor " + professor + " was not found");
        return optionalSections.get();
    }
}
