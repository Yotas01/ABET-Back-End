package edu.javeriana.abetbackend.UseCases.Reviews.Services.CRUD;

import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.AssessmentToolFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.PerformanceIndicatorFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.SectionFinder;
import edu.javeriana.abetbackend.Entities.*;
import edu.javeriana.abetbackend.Entities.DTOs.CourseReview;
import edu.javeriana.abetbackend.Entities.DTOs.SectionAssessmentToolDTO;
import edu.javeriana.abetbackend.Entities.DTOs.SectionPerformanceIndicatorDTO;
import edu.javeriana.abetbackend.Entities.DTOs.SectionReview;
import edu.javeriana.abetbackend.Exceptions.AlreadyExists;
import edu.javeriana.abetbackend.Exceptions.Inconsistent;
import edu.javeriana.abetbackend.Repositories.SectionAssessmentToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseReviewService {

    @Autowired
    private CourseFinder courseFinder;
    @Autowired
    private SectionFinder sectionFinder;
    @Autowired
    private SectionAssessmentToolCRUD sectionATService;
    @Autowired
    private SectionAssessmentToolRepository sectionATRepository;
    @Autowired
    private SectionPerformanceIndicatorCRUD sectionPIService;
    @Autowired
    private AssessmentToolFinder assessmentToolFinder;
    @Autowired
    private PerformanceIndicatorFinder performanceIndicatorFinder;

    public CourseReview getCourseForReview(Integer courseNumber, Integer sectionNumber, Integer semester){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber,sectionNumber, semester);
        Optional<List<SectionAssessmentTool>> sectionAssessmentToolList = sectionATRepository.findAllBySectionAndSemester(section, semester);
        if (sectionAssessmentToolList.isPresent() && !sectionAssessmentToolList.get().isEmpty() && sectionAssessmentToolList.get().get(0).getDraft() == 0)
            throw new AlreadyExists("The review for the course " + courseNumber + " and the section " + sectionNumber +
                " for the semester " + semester + " has already been made");
        return new CourseReview(course,section);
    }

    public SectionReview getSectionReview(Integer courseNumber, Integer sectionNumber, Integer semester) {
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber,sectionNumber, semester);
        Optional<List<SectionAssessmentTool>> sectionAssessmentToolList = sectionATRepository.findAllBySectionAndSemester(section, semester);

        if(sectionAssessmentToolList.isEmpty() || sectionAssessmentToolList.get().isEmpty()){
            throw new NotFound("The section assessment tools for the course: " + courseNumber + " section: " +
                    sectionNumber + " and semester " + semester + " was not found");
        }

        SectionReview sectionReview = new SectionReview();
        sectionReview.setCourseNumber(courseNumber);
        sectionReview.setSectionNumber(sectionNumber);
        sectionReview.setSemester(semester);

        List<SectionAssessmentToolDTO> dtoList = new ArrayList<>();
        sectionAssessmentToolList.get().forEach(sat -> dtoList.add(new SectionAssessmentToolDTO(sat)));

        sectionReview.setSectionAssessmentTools(dtoList);

        return sectionReview;
    }

    public void reviewCourseSection(Integer courseNumber, Integer sectionNumber, Integer semester, SectionReview sectionReview) {
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber,sectionNumber,semester);

        validateCourseAndSectionData(courseNumber, sectionNumber, semester, sectionReview);

        for(SectionAssessmentToolDTO satDTO: sectionReview.getSectionAssessmentTools()) {
            saveSectionAssessmentTool(courseNumber, sectionNumber, semester, section, satDTO);
        }
    }

    public void updateSectionReview(Integer courseNumber, Integer sectionNumber, Integer semester, SectionReview sectionReview) {
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber,sectionNumber,semester);

        validateCourseAndSectionData(courseNumber, sectionNumber, semester, sectionReview);

        for(SectionAssessmentToolDTO satDTO: sectionReview.getSectionAssessmentTools()) {
            saveSectionAssessmentTool(courseNumber, sectionNumber, semester, section, satDTO);
        }
    }

    private void saveSectionAssessmentTool(Integer courseNumber, Integer sectionNumber, Integer semester, Section section, SectionAssessmentToolDTO satDTO) {
        Long raeId = satDTO.getRaeId();
        Long assessmentToolId = satDTO.getAssessmentToolId();
        SectionAssessmentTool sat = sectionATService.addSectionAssessmentTool(courseNumber, sectionNumber, semester,
                raeId, assessmentToolId, new SectionAssessmentTool(satDTO));
        for (SectionPerformanceIndicatorDTO spiDTO: satDTO.getSectionPerformanceIndicators()) {
            saveSectionPerformanceIndicator(courseNumber, sectionNumber, semester, raeId, assessmentToolId, sat, spiDTO);
        }
    }

    private void saveSectionPerformanceIndicator(Integer courseNumber, Integer sectionNumber, Integer semester, Long raeId, Long assessmentToolId, SectionAssessmentTool sat, SectionPerformanceIndicatorDTO spiDTO) {
        Long performanceIndicatorId = spiDTO.getPerformanceIndicatorId();
        PerformanceIndicator performanceIndicator = performanceIndicatorFinder
                .findPerformanceIndicatorFromCourse(courseNumber, raeId, assessmentToolId, performanceIndicatorId);
        SectionPerformanceIndicator spi = sectionPIService
                .addSectionPerformanceIndicator(courseNumber, sectionNumber, semester, raeId, assessmentToolId,
                        performanceIndicatorId, sat.getSectionAssessmentToolId(),
                        new SectionPerformanceIndicator(spiDTO));
    }
    private void validateCourseAndSectionData(Integer courseNumber, Integer sectionNumber, Integer semester, SectionReview sectionReview) {
        if(!sectionReview.getCourseNumber().equals(courseNumber))
            throw new Inconsistent("The section review course number " + sectionReview.getCourseNumber() +
                    " is not the same as the course number " + courseNumber);
        if(!sectionReview.getSectionNumber().equals(sectionNumber))
            throw new Inconsistent("The section review section number " + sectionReview.getSectionNumber() +
                    " is not the same as the section number " + sectionNumber);
        if(!sectionReview.getSemester().equals(semester))
            throw new Inconsistent("The section semester " + sectionReview.getSemester()
                    + " is not the same as the semester " + semester);
    }
}
