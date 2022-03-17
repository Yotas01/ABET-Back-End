package edu.javeriana.abetbackend.Entities.DTOs;


import edu.javeriana.abetbackend.Entities.PerformanceIndicator;

public class PerformanceIndicatorDTO {

    private Long PerformanceIndicatorId;
    private String description;
    private Integer percentage;
    private Integer exemplary;
    private Integer competent;
    private Integer below;
    private Long assessmentTool;

    public PerformanceIndicatorDTO() {
    }

    public PerformanceIndicatorDTO(PerformanceIndicator pi) {
        this.PerformanceIndicatorId = pi.getPerformanceIndicatorId();
        this.description = pi.getDescription();
        this.percentage = pi.getPercentage();
        this.exemplary = pi.getExemplary();
        this.competent = pi.getCompetent();
        this.below = pi.getBelow();
        this.assessmentTool = pi.getAssessmentTool().getAssessmentToolId();
    }

    public Long getPerformanceIndicatorId() {
        return PerformanceIndicatorId;
    }

    public void setPerformanceIndicatorId(Long performanceIndicatorId) {
        PerformanceIndicatorId = performanceIndicatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
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

    public Long getAssessmentTool() {
        return assessmentTool;
    }

    public void setAssessmentTool(Long assessmentTool) {
        this.assessmentTool = assessmentTool;
    }
}
