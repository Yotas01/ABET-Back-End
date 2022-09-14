package edu.javeriana.abetbackend.Entities.Views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "CDIO_SUMMARY_FOR_COURSE")
public class CDIOSummaryForCourse {

    @Id
    @Column(name = "cdio_number")
    private Float cdioNumber;
    @Basic
    @Column(name = "id_course")
    private Long courseId;
    @Basic
    private Double exemplary;
    @Basic
    private Double competent;
    @Basic
    private Double below;

    public CDIOSummaryForCourse(Float cdioNumber, Long courseId, Double exemplary, Double competent, Double below) {
        this.cdioNumber = cdioNumber;
        this.courseId = courseId;
        this.exemplary = exemplary;
        this.competent = competent;
        this.below = below;
    }

    public CDIOSummaryForCourse() {
    }

    public Float getCdioNumber() {
        return cdioNumber;
    }

    public void setCdioNumber(Float cdioNumber) {
        this.cdioNumber = cdioNumber;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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
