package edu.javeriana.abetbackend.Entities.Views;

import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Ids.Course_has_CdioId;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "CDIO_SUMMARY_FOR_COURSE")
public class CDIOSummaryForCourse {

    @EmbeddedId
    private Course_has_CdioId id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "id_course")
    private Course course;
    @ManyToOne
    @MapsId("cdioNumber")
    @JoinColumn(name = "cdio_number")
    private CDIO cdio;
    @Basic
    private Double exemplary;
    @Basic
    private Double competent;
    @Basic
    private Double below;

    public CDIOSummaryForCourse(Course_has_CdioId id, Course course, CDIO cdio, Double exemplary, Double competent, Double below) {
        this.id = id;
        this.course = course;
        this.cdio = cdio;
        this.exemplary = exemplary;
        this.competent = competent;
        this.below = below;
    }

    public CDIOSummaryForCourse() {
    }

    public Course_has_CdioId getId() {
        return id;
    }

    public void setId(Course_has_CdioId id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CDIO getCdio() {
        return cdio;
    }

    public void setCdio(CDIO cdio) {
        this.cdio = cdio;
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
