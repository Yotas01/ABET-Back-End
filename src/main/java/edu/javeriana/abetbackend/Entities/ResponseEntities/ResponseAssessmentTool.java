package edu.javeriana.abetbackend.Entities.ResponseEntities;


import edu.javeriana.abetbackend.Entities.AssessmentTool;

import java.util.ArrayList;
import java.util.List;

public class ResponseAssessmentTool {

    private Long AssessmentToolId;
    private String description;
    private Integer value;
    private Integer totalStudents;
    private String rae;
    private List<String> performanceIndicators;

    public ResponseAssessmentTool() {
        this.performanceIndicators = new ArrayList<>();
    }

    public ResponseAssessmentTool(AssessmentTool assessmentTool) {
        this.AssessmentToolId = assessmentTool.getAssessmentToolId();
        this.description = assessmentTool.getDescription();
        this.value = assessmentTool.getValue();
        this.totalStudents = assessmentTool.getTotalStudents();
        this.rae = assessmentTool.getRae().getDescription();
        this.performanceIndicators = new ArrayList<>();
        assessmentTool.getPerformanceIndicators().forEach(pi -> this.performanceIndicators.add(pi.getDescription()));
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

    public List<String> getPerformanceIndicators() {
        return performanceIndicators;
    }

    public void setPerformanceIndicators(List<String> performanceIndicators) {
        this.performanceIndicators = performanceIndicators;
    }
}
