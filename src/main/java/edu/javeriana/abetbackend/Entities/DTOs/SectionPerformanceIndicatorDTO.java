package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.SectionPerformanceIndicator;

public class SectionPerformanceIndicatorDTO {
    private Long id;
    private Long sectionAssessmentToolId;
    private Long performanceIndicatorId;
    private Integer exemplary;
    private Integer competent;
    private Integer below;
    private boolean draft;

    public SectionPerformanceIndicatorDTO(SectionPerformanceIndicator pi) {
        this.id = pi.getSectionPerformanceIndicatorId();
        this.sectionAssessmentToolId = pi.getSectionAssessmentTool().getSectionAssessmentToolId();
        this.performanceIndicatorId = pi.getPerformanceIndicator().getPerformanceIndicatorId();
        this.exemplary = pi.getExemplary();
        this.competent = pi.getCompetent();
        this.below = pi.getBelow();
        this.draft = pi.getDraft() == 1;
    }

    public SectionPerformanceIndicatorDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSectionAssessmentToolId() {
        return sectionAssessmentToolId;
    }

    public void setSectionAssessmentToolId(Long sectionAssessmentToolId) {
        this.sectionAssessmentToolId = sectionAssessmentToolId;
    }

    public Long getPerformanceIndicatorId() {
        return performanceIndicatorId;
    }

    public void setPerformanceIndicatorId(Long performanceIndicatorId) {
        this.performanceIndicatorId = performanceIndicatorId;
    }

    public Integer getExemplary() {
        return exemplary;
    }

    public void setExemplary(Integer exemplary) {
        this.exemplary = exemplary;
    }

    public Integer getCompetent() {
        return competent;
    }

    public void setCompetent(Integer competent) {
        this.competent = competent;
    }

    public Integer getBelow() {
        return below;
    }

    public void setBelow(Integer below) {
        this.below = below;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }
}
