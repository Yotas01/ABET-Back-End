package edu.javeriana.abetbackend.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.CRUD.Services.Find.AssessmentToolFinder;
import edu.javeriana.abetbackend.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.CRUD.Services.Find.RAEFinder;
import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain.FoundCourseDoesNotContainFoundRae;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain.FoundRAEDoesNotContainFoundAssessmentTool;
import edu.javeriana.abetbackend.Repositories.AssessmentToolRepository;
import edu.javeriana.abetbackend.Repositories.RAERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssessmentToolCRUD {

    @Autowired
    private AssessmentToolRepository assessmentToolRepository;
    @Autowired
    private AssessmentToolFinder assessmentToolFinder;
    @Autowired
    private RAEFinder raeFinder;
    @Autowired
    private RAERepository raeRepository;
    @Autowired
    private CourseFinder courseFinder;

    public void saveAssessmentTool(AssessmentTool assessmentTool, Long raeId, Integer courseNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        RAE rae = raeFinder.findRAEById(raeId);
        if(!rae.getCourse().equals(course))
            throw new FoundCourseDoesNotContainFoundRae("The course " + course.getName() + " does not contain the" +
                    " found RAE" + raeId);
        rae.addAssessmentTool(assessmentTool);
        assessmentTool.setRae(rae);
        raeRepository.save(rae);
        assessmentToolRepository.save(assessmentTool);
    }

    public AssessmentTool updateAssessmentTool(AssessmentTool assessmentTool, Long raeId, Integer courseNumber, Long assessmentToolId){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        RAE rae = raeFinder.findRAEById(raeId);
        if(!rae.getCourse().equals(course))
            throw new FoundCourseDoesNotContainFoundRae("The course " + course.getName() + " does not contain the" +
                    " found RAE" + raeId);
        AssessmentTool assessmentToolToUpdate = assessmentToolFinder.findById(assessmentToolId);
        if (!assessmentToolToUpdate.getRae().equals(rae))
            throw new FoundRAEDoesNotContainFoundAssessmentTool("The rae " + raeId + " does not contain the" +
                    " found assessment tool" + assessmentToolToUpdate.getAssessmentToolId());
        assessmentToolToUpdate.setRae(rae);
        assessmentToolToUpdate.setTotalStudents(assessmentTool.getTotalStudents());
        assessmentToolToUpdate.setDescription(assessmentTool.getDescription());
        assessmentToolToUpdate.setValue(assessmentTool.getValue());
        assessmentToolToUpdate.setPerformanceIndicators(new ArrayList<>(assessmentTool.getPerformanceIndicators()));
        assessmentToolRepository.save(assessmentToolToUpdate);
        return assessmentToolToUpdate;
    }

    public AssessmentTool deleteAssessmentTool(Long assessmentToolId, Long raeId, Integer courseNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        RAE rae = raeFinder.findRAEById(raeId);
        if(!rae.getCourse().equals(course))
            throw new FoundCourseDoesNotContainFoundRae("The course " + course.getName() + " does not contain the" +
                    " found RAE" + raeId);
        AssessmentTool assessmentToolToUpdate = assessmentToolFinder.findById(assessmentToolId);
        if (!assessmentToolToUpdate.getRae().equals(rae))
            throw new FoundRAEDoesNotContainFoundAssessmentTool("The rae " + raeId + " does not contain the" +
                    " found assessment tool" + assessmentToolToUpdate.getAssessmentToolId());
        AssessmentTool assessmentToolToDelete = assessmentToolFinder.findById(assessmentToolId);
        rae.removeAssessmentTool(assessmentToolToDelete);
        assessmentToolRepository.delete(assessmentToolToDelete);
        raeRepository.save(rae);
        return assessmentToolToDelete;
    }
}
