package edu.javeriana.abetbackend.Entities.Views;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SectionReport {

    @Id
    @Column(name = "idSection")
    private Long sectionId;
    @Basic
    @Column(name = "idAssessmentTool")
    private Long assessmentToolId;
    @Basic
    private Integer semester;
    @Basic
    private Integer totalStudents;
    @Basic
    private Double exemplary;
    @Basic
    private Double competent;
    @Basic
    private Double below;

    public SectionReport(Long sectionId, Long assessmentToolId, Integer semester, Integer totalStudents, Double exemplary, Double competent, Double below) {
        this.sectionId = sectionId;
        this.assessmentToolId = assessmentToolId;
        this.semester = semester;
        this.totalStudents = totalStudents;
        this.exemplary = exemplary;
        this.competent = competent;
        this.below = below;
    }

    public SectionReport() {
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

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
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
