package edu.javeriana.abetbackend.UseCases.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.PerformanceIndicator;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.PerformanceIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceIndicatorFinder {

    @Autowired
    private PerformanceIndicatorRepository performanceIndicatorRepository;
    @Autowired
    private AssessmentToolFinder assessmentToolFinder;
    @Autowired
    private RAEFinder raeFinder;
    @Autowired
    private CourseFinder courseFinder;

    public PerformanceIndicator findPerformanceIndicatorById(Long performanceIndicatorId){
        Optional<PerformanceIndicator> performanceIndicator = performanceIndicatorRepository.findById(performanceIndicatorId);
        if (performanceIndicator.isEmpty())
            throw new NotFound("The performance indicator with id " + performanceIndicatorId +
                    " was not found");
        return performanceIndicator.get();
    }

    public PerformanceIndicator findPerformanceIndicatorFromCourse(Integer courseNumber, Long raeId, Long assessmentToolId,
                                                                   Long performanceIndicatorId){
        AssessmentTool assessmentTool = assessmentToolFinder
                .findAssessmentToolByCourseAndRAEId(courseNumber,raeId,assessmentToolId);
        PerformanceIndicator pi = findPerformanceIndicatorById(performanceIndicatorId);
        if(!pi.getAssessmentTool().equals(assessmentTool))
            throw new DoesNotContain("The assessment tool " + assessmentToolId
            + " does not contain the performance indicator " + performanceIndicatorId);
        return pi;
    }

    public List<PerformanceIndicator> getPerformanceIndicatorsFromAssessmentTool(Long assessmentToolId){
        AssessmentTool assessmentTool = assessmentToolFinder.findById(assessmentToolId);
        Optional<List<PerformanceIndicator>> performanceIndicators = performanceIndicatorRepository
                .findByAssessmentTool(assessmentTool);
        if (performanceIndicators.isEmpty() || performanceIndicators.get().isEmpty())
            throw new NotFound("There were no performance indicators found for the" +
                    " assessment tool " + assessmentToolId);
        return performanceIndicators.get();
    }
}
