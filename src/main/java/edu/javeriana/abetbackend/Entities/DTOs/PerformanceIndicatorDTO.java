package edu.javeriana.abetbackend.Entities.DTOs;


import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.PerformanceIndicator;

import java.util.ArrayList;
import java.util.List;

public class PerformanceIndicatorDTO {

    private Long id;
    private Long raeId;
    private String description;
    private Double percentage;
    private List<Long> assessmentTools;

    public PerformanceIndicatorDTO() {
        this.assessmentTools = new ArrayList<>();
    }

    public PerformanceIndicatorDTO(PerformanceIndicator pi) {
        this.id = pi.getId();
        this.description = pi.getDescription();
        this.percentage = pi.getPercentage();
        this.assessmentTools = new ArrayList<>(pi.getAssessmentTools().stream().map(AssessmentTool::getAssessmentToolId).toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRaeId() {
        return raeId;
    }

    public void setRaeId(Long raeId) {
        this.raeId = raeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public List<Long> getAssessmentTools() {
        return assessmentTools;
    }

    public void setAssessmentTools(List<Long> assessmentTools) {
        this.assessmentTools = assessmentTools;
    }
}
