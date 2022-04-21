package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.SectionAssessmentTool;
import edu.javeriana.abetbackend.Entities.SectionPerformanceIndicator;

import java.util.ArrayList;
import java.util.List;

public class SectionAssessmentToolDTO {
    private Long id;
    private Integer courseNumber;
    private Integer sectionNumber;
    private Long raeId;
    private Long assessmentToolId;
    private Integer totalStudents;
    private Integer semester;
    private boolean draft;
    private List<SectionPerformanceIndicatorDTO> sectionPerformanceIndicators;

    public SectionAssessmentToolDTO() {
    }

    public SectionAssessmentToolDTO(SectionAssessmentTool sectionAssessmentTool) {
        this.id = sectionAssessmentTool.getSectionAssessmentToolId();
        this.courseNumber = sectionAssessmentTool.getSection().getCourse().getNumber();
        this.sectionNumber = sectionAssessmentTool.getSection().getNumber();
        this.assessmentToolId = sectionAssessmentTool.getAssessmentTool().getAssessmentToolId();
        this.totalStudents = sectionAssessmentTool.getTotalStudents();
        this.semester = sectionAssessmentTool.getSemester();
        this.draft = sectionAssessmentTool.getDraft() == 1;
        this.sectionPerformanceIndicators = new ArrayList<>();
        sectionAssessmentTool.getSectionPerformanceIndicators()
                .forEach(spi -> this.sectionPerformanceIndicators.add(new SectionPerformanceIndicatorDTO(spi)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Long getRaeId() {
        return raeId;
    }

    public void setRaeId(Long raeId) {
        this.raeId = raeId;
    }

    public Integer getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(Integer sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public Long getAssessmentToolId() {
        return assessmentToolId;
    }

    public void setAssessmentToolId(Long assessmentToolId) {
        this.assessmentToolId = assessmentToolId;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public List<SectionPerformanceIndicatorDTO> getSectionPerformanceIndicators() {
        return sectionPerformanceIndicators;
    }

    public void setSectionPerformanceIndicators(List<SectionPerformanceIndicatorDTO> sectionPerformanceIndicators) {
        this.sectionPerformanceIndicators = sectionPerformanceIndicators;
    }
}
