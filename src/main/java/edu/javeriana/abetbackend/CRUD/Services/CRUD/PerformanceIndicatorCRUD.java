package edu.javeriana.abetbackend.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.CRUD.Services.Find.AssessmentToolFinder;
import edu.javeriana.abetbackend.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.CRUD.Services.Find.PerformanceIndicatorFinder;
import edu.javeriana.abetbackend.CRUD.Services.Find.RAEFinder;
import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.PerformanceIndicator;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import edu.javeriana.abetbackend.Repositories.AssessmentToolRepository;
import edu.javeriana.abetbackend.Repositories.PerformanceIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PerformanceIndicatorCRUD {

    @Autowired
    private PerformanceIndicatorFinder performanceIndicatorFinder;
    @Autowired
    private PerformanceIndicatorRepository performanceIndicatorRepository;
    @Autowired
    private AssessmentToolFinder assessmentToolFinder;
    @Autowired
    private AssessmentToolRepository assessmentToolRepository;
    @Autowired
    private RAEFinder raeFinder;
    @Autowired
    private CourseFinder courseFinder;

    public void addPerformanceIndicator(PerformanceIndicator performanceIndicator, Integer courseNumber, Long raeId, Long assessmentToolId){
        AssessmentTool assessmentTool = findCourseRaeAndAssessmentTool(courseNumber, raeId, assessmentToolId);
        assessmentTool.addPerformanceIndicator(performanceIndicator);
        performanceIndicator.setAssessmentTool(assessmentTool);
        performanceIndicatorRepository.save(performanceIndicator);
        assessmentToolRepository.save(assessmentTool);
    }

    public PerformanceIndicator updatePerformanceIndicator(PerformanceIndicator performanceIndicator, Integer courseNumber, Long raeId,
                                                           Long assessmentToolId, Long performanceIndicatorId){
        AssessmentTool assessmentTool = findCourseRaeAndAssessmentTool(courseNumber, raeId, assessmentToolId);
        PerformanceIndicator performanceIndicatorToUpdate = performanceIndicatorFinder
                .findPerformanceIndicatorById(performanceIndicatorId);
        performanceIndicatorToUpdate.setDescription(performanceIndicator.getDescription());
        performanceIndicatorToUpdate.setPercentage(performanceIndicator.getPercentage());
        performanceIndicatorToUpdate.setAssessmentTool(assessmentTool);
        performanceIndicatorRepository.save(performanceIndicatorToUpdate);
        return performanceIndicatorToUpdate;
    }

    public PerformanceIndicator deletePerformanceIndicator(Integer courseNumber, Long raeId, Long assessmentToolId, Long performanceIndicatorId){
        AssessmentTool assessmentTool = findCourseRaeAndAssessmentTool(courseNumber,raeId,assessmentToolId);
        PerformanceIndicator performanceIndicator = performanceIndicatorFinder.findPerformanceIndicatorById(performanceIndicatorId);
        if(!performanceIndicator.getAssessmentTool().equals(assessmentTool))
            throw new DoesNotContain("The assessment tool with id "
                    + assessmentToolId + "does not contain the performance indicator " + performanceIndicatorId);
        assessmentTool.removePerformanceIndicator(performanceIndicator);
        performanceIndicatorRepository.delete(performanceIndicator);
        return performanceIndicator;
    }

    public void deleteAllPerformanceIndicatorsFromAssessmentTool(Integer courseNumber, Long raeId, Long assessmentToolId) {
        AssessmentTool assessmentTool = findCourseRaeAndAssessmentTool(courseNumber, raeId, assessmentToolId);
        if(assessmentTool.getPerformanceIndicators().isEmpty())
            throw new DoesNotContain("The assessment tool with id "
                    + assessmentToolId + " has no performance indicators");
        performanceIndicatorRepository.deleteAllByAssessmentTool(assessmentTool);
        assessmentTool.getPerformanceIndicators().clear();
        assessmentToolRepository.save(assessmentTool);
    }

    private AssessmentTool findCourseRaeAndAssessmentTool(Integer courseNumber, Long raeId, Long assessmentToolId) {
        Course course = courseFinder.findCourseByNumber(courseNumber);
        RAE rae = raeFinder.findRAEById(raeId);
        if(!rae.getCourse().equals(course))
            throw new DoesNotContain("The course " + course.getName() + " does not contain the" +
                    " found RAE" + raeId);
        AssessmentTool assessmentTool = assessmentToolFinder.findById(assessmentToolId);
        if (!assessmentTool.getRae().equals(rae))
            throw new DoesNotContain("The rae " + raeId + " does not contain the" +
                    " found assessment tool" + assessmentToolId);
        return assessmentTool;
    }
}
