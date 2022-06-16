package edu.javeriana.abetbackend.UseCases.Reviews.Services.Find;

import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.PerformanceIndicatorFinder;
import edu.javeriana.abetbackend.Entities.PerformanceIndicator;
import edu.javeriana.abetbackend.Entities.SectionAssessmentTool;
import edu.javeriana.abetbackend.Entities.SectionPerformanceIndicator;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.SectionPerformanceIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionPerformanceIndicatorFinder {

    @Autowired
    private SectionPerformanceIndicatorRepository repository;
    @Autowired
    private SectionAssessmentToolFinder sectionAssessmentToolFinder;
    @Autowired
    private PerformanceIndicatorFinder performanceIndicatorFinder;

    public List<SectionPerformanceIndicator> findSectionPerformanceIndicatorsBySectionAssessmentTool(Integer courseNumber, Integer sectionNumber, Integer semester,
                                                                                                     Long raeId, Long assessmentToolId,
                                                                                                     Long sectionATId){
        SectionAssessmentTool sectionAssessmentTool = sectionAssessmentToolFinder
                .findSectionATByIdFromCourseNumber(courseNumber,sectionNumber,semester,raeId,assessmentToolId,sectionATId);
        Optional<List<SectionPerformanceIndicator>> sectionPerformanceIndicators =
                repository.findAllBySectionAssessmentTool(sectionAssessmentTool);
        if(sectionPerformanceIndicators.isEmpty() || sectionPerformanceIndicators.get().isEmpty())
            throw new NotFound("There were no section_PI found for" +
                    "section_AT with Id " + sectionATId);
        return sectionPerformanceIndicators.get();
    }

    public SectionPerformanceIndicator findById(Long performanceIndicatorId){
        Optional<SectionPerformanceIndicator> performanceIndicator = repository.findById(performanceIndicatorId);
        if (performanceIndicator.isEmpty())
            throw new NotFound("The section_performanceIndicator with the id " +
                    performanceIndicatorId + " was not found");
        return performanceIndicator.get();
    }

    public SectionPerformanceIndicator findByIdFromCourse(Integer courseNumber, Integer sectionNumber, Integer semester,
                                                          Long raeId, Long assessmentToolId, Long performanceIndicatorId,
                                                          Long sectionATId, Long sectionPIId) {
        SectionAssessmentTool sat = sectionAssessmentToolFinder
                .findSectionATByIdFromCourseNumber(courseNumber, sectionNumber, semester, raeId, assessmentToolId, sectionATId);
        PerformanceIndicator pi = performanceIndicatorFinder
                .findPerformanceIndicatorFromCourse(courseNumber,raeId,assessmentToolId,performanceIndicatorId);
        SectionPerformanceIndicator sectionPI = findById(sectionPIId);
        if(!sectionPI.getSectionAssessmentTool().equals(sat))
            throw new DoesNotContain("The sectionAT " + sectionATId +
                    " does not contain the sectionPI " + sectionPIId);
        if(!sectionPI.getPerformanceIndicator().equals(pi))
            throw new DoesNotContain("The performance indicator " + performanceIndicatorId
            + " does not contain the sectionPI " + sectionPIId);
        return sectionPI;
    }
}
