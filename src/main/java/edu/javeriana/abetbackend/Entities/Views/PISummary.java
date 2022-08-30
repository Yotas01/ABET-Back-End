package edu.javeriana.abetbackend.Entities.Views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table(name = "PI_SUMMARY")
@Immutable
public class PISummary {

    @Id
    @Column(name = "id_performanceindicator")
    private Long performanceIndicatorId;
    @Basic
    @Column(name = "id_section")
    private Long sectionId;
    @Basic
    @Column(name = "id_assessment_tool")
    private Long assessmentToolId;
    @Basic
    @Column(name = "id_rae")
    private Long raeId;
    @Basic
    @Column(name = "total_students")
    private Integer totalStudents;
    @Basic
    private Double value;
    @Basic
    private Double percentage;
    @Basic
    private Double exemplary;
    @Basic
    private Double competent;
    @Basic
    private Double below;

    public PISummary(Long performanceIndicatorId, Long sectionId, Long assessmentToolId, Long raeId, Integer totalStudents, Double value, Double percentage, Double exemplary, Double competent, Double below) {
        this.performanceIndicatorId = performanceIndicatorId;
        this.sectionId = sectionId;
        this.assessmentToolId = assessmentToolId;
        this.raeId = raeId;
        this.totalStudents = totalStudents;
        this.value = value;
        this.percentage = percentage;
        this.exemplary = exemplary;
        this.competent = competent;
        this.below = below;
    }

    public PISummary() {
    }

    public Long getPerformanceIndicatorId() {
        return performanceIndicatorId;
    }

    public void setPerformanceIndicatorId(Long performanceIndicatorId) {
        this.performanceIndicatorId = performanceIndicatorId;
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

    public Long getRaeId() {
        return raeId;
    }

    public void setRaeId(Long raeId) {
        this.raeId = raeId;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
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
}
