package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
public class PerformanceIndicator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPerformanceIndicator")
    private Long PerformanceIndicatorId;
    @Basic
    private String description;
    @Basic
    private Integer percentage;
    @Basic
    private Integer exemplary;
    @Basic
    private Integer competent;
    @Basic
    private Integer below;
    @ManyToOne
    @JoinColumn(name = "idAssessmentTool")
    private AssessmentTool assessmentTool;

    public PerformanceIndicator() {
    }

    public PerformanceIndicator(Long performanceIndicatorId, String description, Integer percentage, Integer exemplary, Integer competent, Integer below, AssessmentTool assessmentTool) {
        PerformanceIndicatorId = performanceIndicatorId;
        this.description = description;
        this.percentage = percentage;
        this.exemplary = exemplary;
        this.competent = competent;
        this.below = below;
        this.assessmentTool = assessmentTool;
    }

    public AssessmentTool getAssessmentTool() {
        return assessmentTool;
    }

    public void setAssessmentTool(AssessmentTool assessmentTool) {
        this.assessmentTool = assessmentTool;
    }

    public Long getPerformanceIndicatorId() {
        return PerformanceIndicatorId;
    }

    public void setPerformanceIndicatorId(Long PerformanceIndicatorId) {
        this.PerformanceIndicatorId = PerformanceIndicatorId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerformanceIndicator that = (PerformanceIndicator) o;
        return Objects.equal(PerformanceIndicatorId, that.PerformanceIndicatorId) && Objects.equal(description, that.description) && Objects.equal(percentage, that.percentage) && Objects.equal(exemplary, that.exemplary) && Objects.equal(competent, that.competent) && Objects.equal(below, that.below) && Objects.equal(assessmentTool, that.assessmentTool);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(PerformanceIndicatorId, description, percentage, exemplary, competent, below, assessmentTool);
    }
}