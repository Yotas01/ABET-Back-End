package edu.javeriana.abetbackend.Entities.Views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table(name = "RETORNAR_TODO")
@Immutable
public class RetornarTodo {

    @Id
    @Column(name = "id_performanceindicator")
    private Long performanceIndicatorId;
    @Basic
    @Column(name = "desc_pi")
    private String performanceIndicatorDescription;
    @Basic
    @Column(name = "id_course")
    private Long courseId;
    @Basic
    @Column(name = "id_sae")
    private Long saeId;
    @Basic
    @Column(name = "id_rae")
    private Long raeId;
    @Basic
    @Column(name = "desc_rae")
    private Long raeDescription;
    @Basic
    @Column(name = "id_assessment_tool")
    private Long assessmentToolId;
    @Basic
    @Column(name = "desc_assessment")
    private Long assessmentToolDescription;
    @Basic
    private String name;
    @Basic
    private Double value;
    @Basic
    private Double percentage;

    public RetornarTodo(Long performanceIndicatorId, String performanceIndicatorDescription, Long courseId, Long saeId, Long raeId, Long raeDescription, Long assessmentToolId, Long assessmentToolDescription, String name, Double value, Double percentage) {
        this.performanceIndicatorId = performanceIndicatorId;
        this.performanceIndicatorDescription = performanceIndicatorDescription;
        this.courseId = courseId;
        this.saeId = saeId;
        this.raeId = raeId;
        this.raeDescription = raeDescription;
        this.assessmentToolId = assessmentToolId;
        this.assessmentToolDescription = assessmentToolDescription;
        this.name = name;
        this.value = value;
        this.percentage = percentage;
    }

    public RetornarTodo() {
    }

    public Long getPerformanceIndicatorId() {
        return performanceIndicatorId;
    }

    public void setPerformanceIndicatorId(Long performanceIndicatorId) {
        this.performanceIndicatorId = performanceIndicatorId;
    }

    public String getPerformanceIndicatorDescription() {
        return performanceIndicatorDescription;
    }

    public void setPerformanceIndicatorDescription(String performanceIndicatorDescription) {
        this.performanceIndicatorDescription = performanceIndicatorDescription;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getSaeId() {
        return saeId;
    }

    public void setSaeId(Long saeId) {
        this.saeId = saeId;
    }

    public Long getRaeId() {
        return raeId;
    }

    public void setRaeId(Long raeId) {
        this.raeId = raeId;
    }

    public Long getRaeDescription() {
        return raeDescription;
    }

    public void setRaeDescription(Long raeDescription) {
        this.raeDescription = raeDescription;
    }

    public Long getAssessmentToolId() {
        return assessmentToolId;
    }

    public void setAssessmentToolId(Long assessmentToolId) {
        this.assessmentToolId = assessmentToolId;
    }

    public Long getAssessmentToolDescription() {
        return assessmentToolDescription;
    }

    public void setAssessmentToolDescription(Long assessmentToolDescription) {
        this.assessmentToolDescription = assessmentToolDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
