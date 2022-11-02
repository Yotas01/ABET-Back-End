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

    public Section findSectionByNumberAndSemester(Integer courseNumber, Integer sectionNumber, Integer semester){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Optional<Section> optionalSection = sectionRepository.findByCourseAndClassNumberAndSemester(course, sectionNumber, semester);
        if(optionalSection.isEmpty())
            throw new NotFound("The section with number " + sectionNumber + " for the course " + courseNumber
                    + " and the semester " + semester + " was not found");
        return optionalSection.get();
    }

    public List<Section> findSectionsFromCourseNumberAndSemester(Integer courseNumber, Integer semester){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Optional<List<Section>> sections = sectionRepository.findAllByCourseAndSemester(course, semester);
        if (sections.isEmpty() || sections.get().isEmpty())
            throw new NotFound("There were no sections found for the course " + courseNumber
                    + " and the semester " + semester);
        return sections.get();
    }

    public List<Section> findByProfessor(String professor, Integer semester){
        Optional<List<Section>> optionalSections = sectionRepository.findAllByProfessorAndSemester(professor, semester);
        if(optionalSections.isEmpty())
            throw new NotFound("The section with the professor " + professor + " was not found");
        return optionalSections.get();
    }
}
