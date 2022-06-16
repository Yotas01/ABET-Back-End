package edu.javeriana.abetbackend.UseCases.Reviews.Services.CRUD;

import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.PerformanceIndicatorFinder;
import edu.javeriana.abetbackend.UseCases.Reviews.Services.Find.SectionAssessmentToolFinder;
import edu.javeriana.abetbackend.UseCases.Reviews.Services.Find.SectionPerformanceIndicatorFinder;
import edu.javeriana.abetbackend.Entities.*;
import edu.javeriana.abetbackend.Repositories.SectionAssessmentToolRepository;
import edu.javeriana.abetbackend.Repositories.SectionPerformanceIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionPerformanceIndicatorCRUD {

    @Autowired
    private SectionPerformanceIndicatorRepository sectionPerformanceIndicatorRepository;
    @Autowired
    private SectionPerformanceIndicatorFinder sectionPerformanceIndicatorFinder;
    @Autowired
    private PerformanceIndicatorFinder performanceIndicatorFinder;
    @Autowired
    private SectionAssessmentToolFinder sectionAssessmentToolFinder;
    @Autowired
    private SectionAssessmentToolRepository sectionAssessmentToolRepository;

    public SectionPerformanceIndicator addSectionPerformanceIndicator(Integer courseNumber, Integer sectionNumber, Integer semester,
                                                                      Long raeId, Long assessmentToolId, Long performanceIndicatorId,
                                                                      Long sectionATId,
                                                                      SectionPerformanceIndicator sectionPerformanceIndicator) {
        SectionAssessmentTool sat = sectionAssessmentToolFinder
                .findSectionATByIdFromCourseNumber(courseNumber,sectionNumber,semester,raeId,assessmentToolId,sectionATId);
        PerformanceIndicator pi = performanceIndicatorFinder
                .findPerformanceIndicatorFromCourse(courseNumber,raeId,assessmentToolId,performanceIndicatorId);
        sectionPerformanceIndicator.setPerformanceIndicator(pi);
        sectionPerformanceIndicator.setSectionAssessmentTool(sat);
        sat.addSectionPerformanceIndicator(sectionPerformanceIndicator);
        sectionPerformanceIndicatorRepository.save(sectionPerformanceIndicator);
        sectionAssessmentToolRepository.save(sat);
        return sectionPerformanceIndicator;
    }

    public SectionPerformanceIndicator updateSectionPerformanceIndicator(Integer courseNumber, Integer sectionNumber, Integer semester,
                                                                      Long raeId, Long assessmentToolId, Long performanceIndicatorId,
                                                                      Long sectionATId, Long sectionPIId,
                                                                      SectionPerformanceIndicator sectionPerformanceIndicator) {
        SectionPerformanceIndicator toUpdate = sectionPerformanceIndicatorFinder
                .findByIdFromCourse(courseNumber,sectionNumber,semester,raeId,assessmentToolId,performanceIndicatorId,
                        sectionATId,sectionPIId);
        toUpdate.setExemplary(sectionPerformanceIndicator.getExemplary());
        toUpdate.setCompetent(sectionPerformanceIndicator.getCompetent());
        toUpdate.setBelow(sectionPerformanceIndicator.getBelow());
        toUpdate.setDraft(sectionPerformanceIndicator.getDraft());
        sectionPerformanceIndicatorRepository.save(toUpdate);
        return toUpdate;
    }

    public SectionPerformanceIndicator deleteSectionPerformanceIndicator(Integer courseNumber, Integer sectionNumber, Integer semester,
                                                                         Long raeId, Long assessmentToolId, Long performanceIndicatorId,
                                                                         Long sectionATId, Long sectionPIId) {
        SectionPerformanceIndicator toDelete = sectionPerformanceIndicatorFinder
                .findByIdFromCourse(courseNumber,sectionNumber,semester,raeId,assessmentToolId,performanceIndicatorId,
                        sectionATId,sectionPIId);
        sectionPerformanceIndicatorRepository.delete(toDelete);
        return toDelete;
    }
}
