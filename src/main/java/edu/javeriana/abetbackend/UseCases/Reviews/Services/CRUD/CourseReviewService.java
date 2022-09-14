package edu.javeriana.abetbackend.UseCases.Reviews.Services.CRUD;

import edu.javeriana.abetbackend.Entities.DTOs.*;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.SectionPerformanceIndicatorRepository;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.PerformanceIndicatorFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.SectionFinder;
import edu.javeriana.abetbackend.Entities.*;
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
    private PerformanceIndicatorFinder performanceIndicatorFinder;
    @Autowired
    private SectionPerformanceIndicatorRepository sectionPIRepository;

    public CourseReview getCourseForReview(Integer courseNumber, Integer sectionNumber, Integer semester){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber,sectionNumber, semester);

        CourseReview courseReview = new CourseReview(course,section);
        SectionReview sectionReview = new SectionReview();
        sectionReview.setCourseNumber(courseNumber);
        sectionReview.setSectionNumber(sectionNumber);
        sectionReview.setSemester(semester);

        Optional<List<SectionAssessmentTool>> sectionAssessmentToolList = sectionATRepository.findAllBySectionAndSemester(section, semester);

        //This section has already been reviewed, and it is not in draft mode
        if (sectionAssessmentToolList.isPresent() && !sectionAssessmentToolList.get().isEmpty() &&
                sectionAssessmentToolList.get().get(0).getDraft() == 0)
            throw new AlreadyExists("The review for the course " + courseNumber + " and the section " + sectionNumber +
                    " for the semester " + semester + " has already been made");

        //This section has been reviewed, but it is still in draft mode
        else if(sectionAssessmentToolList.isPresent() && !sectionAssessmentToolList.get().isEmpty() &&
                sectionAssessmentToolList.get().get(0).getDraft() == 1)
            sectionAssessmentToolList.get().forEach(sectionReview::addSectionAssessmentTool);

        //This section nas not been reviewed
        else
            sectionReview.setSectionAssessmentTools(createNewSectionReview(courseReview));

        courseReview.setSectionReview(sectionReview);
        return courseReview;
    }

    private List<SectionAssessmentToolDTO> createNewSectionReview(CourseReview courseReview) {
        List<SectionAssessmentToolDTO> sectionAssessmentTools = new ArrayList<>();
        for (RAEDTO rae:courseReview.getRAEs()) {
            for (AssessmentToolDTO assessmentTool : rae.getAssessmentTools()) {
                List<SectionPerformanceIndicatorDTO> sectionPIs = new ArrayList<>();
                for (PerformanceIndicatorDTO performanceIndicator: assessmentTool.getPerformanceIndicators()) {
                    sectionPIs.add(new SectionPerformanceIndicatorDTO(null, null,
                            performanceIndicator.getPerformanceIndicatorId(), 0, 0, 0, true));
                }
                sectionAssessmentTools.add(new SectionAssessmentToolDTO(null, courseReview.getNumber(),
                        courseReview.getSection().getNumber(), rae.getRaeId(), assessmentTool.getAssessmentToolId(), 0,
                        courseReview.getSection().getSemester(),true,sectionPIs));
            }
        }
        return sectionAssessmentTools;
    }

    public void reviewCourseSection(Integer courseNumber, Integer sectionNumber, Integer semester, SectionReview sectionReview) {
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber,sectionNumber,semester);

        validateCourseAndSectionData(courseNumber, sectionNumber, semester, sectionReview);

        Optional<List<SectionAssessmentTool>> sectionAssessmentTools = sectionATRepository.findAllBySectionAndSemester(section, semester);
        if(sectionAssessmentTools.isPresent() && !sectionAssessmentTools.get().isEmpty())
            updateSectionReview(sectionReview);
        else {
            for (SectionAssessmentToolDTO satDTO : sectionReview.getSectionAssessmentTools())
                saveSectionAssessmentTool(courseNumber, sectionNumber, semester, section, satDTO);
        }
    }

    public void updateSectionReview(SectionReview sectionReview) {
        for (SectionAssessmentToolDTO sat:sectionReview.getSectionAssessmentTools()) {
            updateSectionAssessmentTool(sat);
        }
    }

    private void updateSectionAssessmentTool(SectionAssessmentToolDTO sat) {
        Optional<SectionAssessmentTool> satToUpdate = sectionATRepository.findById(sat.getId());
        if(satToUpdate.isEmpty())
            throw new NotFound("The section assessment tool with id " + sat.getId() + " was not found");
        for (SectionPerformanceIndicatorDTO spi: sat.getSectionPerformanceIndicators()) {
            updateSectionPerformanceIndicator(spi);
        }
        satToUpdate.get().setTotalStudents(sat.getTotalStudents());
        if(sat.isDraft())
            satToUpdate.get().setDraft(1);
        else
            satToUpdate.get().setDraft(0);
        sectionATRepository.save(satToUpdate.get());
    }

    private void updateSectionPerformanceIndicator(SectionPerformanceIndicatorDTO spi) {
        Optional<SectionPerformanceIndicator> spiToUpdate = sectionPIRepository.findById(spi.getId());
        if(spiToUpdate.isEmpty())
            throw new NotFound("The section performance indicator with the id " + spi.getId() + " was not found");
        spiToUpdate.get().setExemplary(spi.getExemplary());
        spiToUpdate.get().setCompetent(spi.getCompetent());
        spiToUpdate.get().setBelow(spi.getBelow());
        if(spi.isDraft())
            spiToUpdate.get().setDraft(1);
        else
            spiToUpdate.get().setDraft(0);
        sectionPIRepository.save(spiToUpdate.get());
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
