package edu.javeriana.abetbackend.UseCases.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.PerformanceIndicator;
import edu.javeriana.abetbackend.Entities.RAE;
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

    public PerformanceIndicator findPerformanceIndicatorById(Long performanceIndicatorId){
        Optional<PerformanceIndicator> performanceIndicator = performanceIndicatorRepository.findById(performanceIndicatorId);
        if (performanceIndicator.isEmpty())
            throw new NotFound("The performance indicator with id " + performanceIndicatorId +
                    " was not found");
        return performanceIndicator.get();
    }

    public PerformanceIndicator findFromCourseRAEAndId(Integer courseNumber, Long raeId,
                                                                   Long performanceIndicatorId){
        RAE rae = raeFinder.findRAEByCourseAndRaeId(courseNumber, raeId);
        PerformanceIndicator pi = findPerformanceIndicatorById(performanceIndicatorId);
        if(!pi.getRae().equals(rae))
            throw new DoesNotContain("The rae " + raeId
            + " does not contain the performance indicator " + performanceIndicatorId);
        return pi;
    }

    public List<PerformanceIndicator> getAllByRAE(Integer courseNumber, Long raeId){
        RAE rae = raeFinder.findRAEByCourseAndRaeId(courseNumber, raeId);
        return rae.getPerformanceIndicators();
    }
}
