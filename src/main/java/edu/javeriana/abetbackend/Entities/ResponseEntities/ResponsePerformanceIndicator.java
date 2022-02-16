package edu.javeriana.abetbackend.Entities.ResponseEntities;


import edu.javeriana.abetbackend.Entities.PerformanceIndicator;

public class ResponsePerformanceIndicator {

    private Long PerformanceIndicatorId;
    private String description;
    private Integer percentage;
    private Integer exemplary;
    private Integer competent;
    private Integer below;
    private String assessmentTool;

    public ResponsePerformanceIndicator() {
    }

    public ResponsePerformanceIndicator(PerformanceIndicator pi) {
        this.PerformanceIndicatorId = pi.getPerformanceIndicatorId();
        this.description = pi.getDescription();
        this.percentage = pi.getPercentage();
        this.exemplary = pi.getExemplary();
        this.competent = pi.getCompetent();
        this.below = pi.getBelow();
        this.assessmentTool = pi.getAssessmentTool().getDescription();
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

    public String getAssessmentTool() {
        return assessmentTool;
    }

    public void setAssessmentTool(String assessmentTool) {
        this.assessmentTool = assessmentTool;
    }
}
