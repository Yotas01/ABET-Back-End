package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.SectionAssessmentTool;
import edu.javeriana.abetbackend.Entities.SectionPerformanceIndicator;

import java.util.ArrayList;
import java.util.List;

public class SectionAssessmentToolDTO {
    private Long id;
    private Integer courseNumber;
    private Long sectionId;
    private Long assessmentToolId;
    private Integer totalStudents;
    private Integer semester;
    private boolean draft;
    private List<SectionPerformanceIndicator> sectionPerformanceIndicators;

    public SectionAssessmentToolDTO() {
    }

    public SectionAssessmentToolDTO(SectionAssessmentTool sectionAssessmentTool) {
        this.id = sectionAssessmentTool.getSectionAssessmentToolId();
        this.courseNumber = sectionAssessmentTool.getSection().getCourse().getNumber();
        this.sectionId = sectionAssessmentTool.getSection().getSectionId();
        this.assessmentToolId = sectionAssessmentTool.getAssessmentTool().getAssessmentToolId();
        this.totalStudents = sectionAssessmentTool.getTotalStudents();
        this.semester = sectionAssessmentTool.getSemester();
        this.draft = sectionAssessmentTool.getDraft() == 1;
        this.sectionPerformanceIndicators = new ArrayList<>(sectionAssessmentTool.getSectionPerformanceIndicators());
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

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
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

    public List<SectionPerformanceIndicator> getSectionPerformanceIndicators() {
        return sectionPerformanceIndicators;
    }

    public void setSectionPerformanceIndicators(List<SectionPerformanceIndicator> sectionPerformanceIndicators) {
        this.sectionPerformanceIndicators = sectionPerformanceIndicators;
    }
}
