package edu.javeriana.abetbackend.UseCases.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.DTOs.AssessmentToolDTO;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.AssessmentToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssessmentToolFinder {

    @Autowired
    private AssessmentToolRepository repository;
    @Autowired
    private CourseFinder courseFinder;

    public AssessmentTool findById(Long id){
        Optional<AssessmentTool> assessmentTool = repository.findById(id);
        if (assessmentTool.isEmpty())
            throw new NotFound("The assessment tool with id " + id + " was not found");
        return assessmentTool.get();
    }

    public AssessmentTool findByCourseAndId(Integer courseNumber, Long assessmentToolId){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Optional<AssessmentTool> assessmentTool = repository.findById(assessmentToolId);
        if (assessmentTool.isEmpty())
            throw new NotFound("The assessment tool with id " + assessmentToolId + " was not found");
        if (!assessmentTool.get().getCourse().equals(course))
            throw new DoesNotContain("The course " + courseNumber + " does not contain the assessment tool " + assessmentToolId);
        return assessmentTool.get();
    }

    public List<AssessmentTool> getAllByCourse(Integer courseNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Optional<List<AssessmentTool>> assessmentTools = repository.findAllByCourse(course);
        if(assessmentTools.isEmpty() || assessmentTools.get().isEmpty())
            throw new NotFound("There are no assessment tools for the course " + courseNumber);
        return assessmentTools.get();
    }
}
