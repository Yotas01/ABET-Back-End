package edu.javeriana.abetbackend.Entities.DTOs;


import edu.javeriana.abetbackend.Entities.AssessmentTool;

import java.util.ArrayList;
import java.util.List;

public class AssessmentToolDTO {

    private Long AssessmentToolId;
    private String description;
    private Integer value;
    private Integer totalStudents;
    private String rae;
    private List<PerformanceIndicatorDTO> performanceIndicators;

    public AssessmentToolDTO() {
        this.performanceIndicators = new ArrayList<>();
    }

    public AssessmentToolDTO(AssessmentTool assessmentTool) {
        this.AssessmentToolId = assessmentTool.getAssessmentToolId();
        this.description = assessmentTool.getDescription();
        this.value = assessmentTool.getValue();
        this.totalStudents = assessmentTool.getTotalStudents();
        this.rae = assessmentTool.getRae().getDescription();
        this.performanceIndicators = new ArrayList<>();
        assessmentTool.getPerformanceIndicators().forEach(pi -> this.performanceIndicators.add(new PerformanceIndicatorDTO(pi)));
    }

    public Long getAssessmentToolId() {
        return AssessmentToolId;
    }

    public void setAssessmentToolId(Long assessmentToolId) {
        AssessmentToolId = assessmentToolId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public String getRae() {
        return rae;
    }

    public void setRae(String rae) {
        this.rae = rae;
    }

    public List<PerformanceIndicatorDTO> getPerformanceIndicators() {
        return performanceIndicators;
    }

    public void setPerformanceIndicators(List<PerformanceIndicatorDTO> performanceIndicators) {
        this.performanceIndicators = performanceIndicators;
    }
}
