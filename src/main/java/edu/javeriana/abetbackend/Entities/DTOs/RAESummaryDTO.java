package edu.javeriana.abetbackend.Entities.DTOs;

import java.util.ArrayList;
import java.util.List;

public class RAESummaryDTO {

    private Long raeId;
    private String raeDescription;
    private List<String> assessmentTools;
    private Double exemplary;
    private Double competent;
    private Double below;

    public RAESummaryDTO(Long raeId, String raeDescription, Double exemplary, Double competent, Double below) {
        this.raeId = raeId;
        this.raeDescription = raeDescription;
        this.assessmentTools = new ArrayList<>();
        this.exemplary = exemplary;
        this.competent = competent;
        this.below = below;
    }

    public RAESummaryDTO() {
        this.assessmentTools = new ArrayList<>();
    }

    public Long getRaeId() {
        return raeId;
    }

    public void setRaeId(Long raeId) {
        this.raeId = raeId;
    }

    public String getRaeDescription() {
        return raeDescription;
    }

    public void setRaeDescription(String raeDescription) {
        this.raeDescription = raeDescription;
    }

    public List<String> getAssessmentTools() {
        return assessmentTools;
    }

    public void setAssessmentTools(List<String> assessmentTools) {
        this.assessmentTools = assessmentTools;
    }

    public Double getExemplary() {
        return exemplary;
    }

    public void setExemplary(Double exemplary) {
        this.exemplary = exemplary;
    }

    public Double getCompetent() {
        return competent;
    }

    public void setCompetent(Double competent) {
        this.competent = competent;
    }

    public Double getBelow() {
        return below;
    }

    public void setBelow(Double below) {
        this.below = below;
    }

    public void addAssessmentTool(String assessmentTool){
        this.assessmentTools.add(assessmentTool);
    }

    public void removeAssessmentTool(String assessmentTool){
        this.assessmentTools.remove(assessmentTool);
    }
}
