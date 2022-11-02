package edu.javeriana.abetbackend.UseCases.CRUD.Services.Relations;

import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.PerformanceIndicator;
import edu.javeriana.abetbackend.Exceptions.AlreadyContains;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import edu.javeriana.abetbackend.Repositories.AssessmentToolRepository;
import edu.javeriana.abetbackend.Repositories.PerformanceIndicatorRepository;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD.PerformanceIndicatorCRUD;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.AssessmentToolFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.PerformanceIndicatorFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AT_PIRelationsService {

    @Autowired
    private AssessmentToolFinder assessmentToolFinder;
    @Autowired
    private PerformanceIndicatorFinder performanceIndicatorFinder;
    @Autowired
    private PerformanceIndicatorRepository piRepository;
    @Autowired
    private AssessmentToolRepository atRepository;

    public PerformanceIndicator addAssessmentToolToPerformanceIndicator(Long atId, Long piId){
        AssessmentTool assessmentTool = assessmentToolFinder.findById(atId);
        PerformanceIndicator performanceIndicator = performanceIndicatorFinder.findPerformanceIndicatorById(piId);

        if(performanceIndicator.getAssessmentTools().contains(assessmentTool))
            throw new AlreadyContains("The performance indicator with id " + piId + " already contains the assessment tool with id " + atId);
        performanceIndicator.addAssessmentTool(assessmentTool);
        assessmentTool.addPerformanceIndicator(performanceIndicator);
        piRepository.save(performanceIndicator);
        atRepository.save(assessmentTool);
        return performanceIndicator;
    }

    public PerformanceIndicator deleteAssessmentToolFromPerformanceIndicator(Long atId, Long piId){
        AssessmentTool assessmentTool = assessmentToolFinder.findById(atId);
        PerformanceIndicator performanceIndicator = performanceIndicatorFinder.findPerformanceIndicatorById(piId);

        if(!performanceIndicator.getAssessmentTools().contains(assessmentTool))
            throw new DoesNotContain("The performance indicator with id " + piId + " does not contain the assessment tool with id " + atId);
        performanceIndicator.removeAssessmentTool(assessmentTool);
        assessmentTool.removePerformanceIndicator(performanceIndicator);
        piRepository.save(performanceIndicator);
        atRepository.save(assessmentTool);
        return performanceIndicator;
    }
}
