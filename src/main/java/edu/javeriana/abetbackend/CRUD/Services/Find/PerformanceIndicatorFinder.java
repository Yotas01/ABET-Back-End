package edu.javeriana.abetbackend.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.PerformanceIndicator;
import edu.javeriana.abetbackend.Exceptions.NotFound.PerformanceIndicatorNotFoundById;
import edu.javeriana.abetbackend.Exceptions.NotFound.PerformanceIndicatorsNotFoundByAssessmentTool;
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
            throw new PerformanceIndicatorNotFoundById("The performance indicator with id " + performanceIndicatorId +
                    " was not found");
        return performanceIndicator.get();
    }

    public List<PerformanceIndicator> getPerformanceIndicatorsFromAssessmentTool(Long assessmentToolId){
        AssessmentTool assessmentTool = assessmentToolFinder.findById(assessmentToolId);
        Optional<List<PerformanceIndicator>> performanceIndicators = performanceIndicatorRepository
                .findByAssessmentTool(assessmentTool);
        if (performanceIndicators.isEmpty() || performanceIndicators.get().isEmpty())
            throw new PerformanceIndicatorsNotFoundByAssessmentTool("There were no performance indicators found for the" +
                    " assessment tool " + assessmentToolId);
        return performanceIndicators.get();
    }
}
