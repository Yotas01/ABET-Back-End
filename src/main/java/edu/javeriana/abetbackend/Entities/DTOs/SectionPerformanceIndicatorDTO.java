package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.SectionPerformanceIndicator;

public class SectionPerformanceIndicatorDTO {
    private Long id;
    private Long sectionAssessmentToolId;
    private PerformanceIndicatorDTO performanceIndicator;
    private Integer exemplary;
    private Integer competent;
    private Integer below;
    private boolean draft;

    public SectionPerformanceIndicatorDTO(SectionPerformanceIndicator pi) {
        this.id = pi.getSectionPerformanceIndicatorId();
        this.sectionAssessmentToolId = pi.getSectionAssessmentTool().getSectionAssessmentToolId();
        this.performanceIndicator = new PerformanceIndicatorDTO(pi.getPerformanceIndicator());
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

    public PerformanceIndicatorDTO getPerformanceIndicator() {
        return performanceIndicator;
    }

    public void setPerformanceIndicator(PerformanceIndicatorDTO performanceIndicator) {
        this.performanceIndicator = performanceIndicator;
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
