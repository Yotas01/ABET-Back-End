package edu.javeriana.abetbackend.Entities.Ids;

import com.google.common.base.Objects;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.Course;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class Course_has_CdioId implements Serializable {
    private static final long serialVersionUID = 9221266263378821083L;
    @ManyToOne
    @JoinColumn(name = "idCourse")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "idCDIO")
    private CDIO cdio;

    @Override
    public String toString() {
        return "Course_has_CdioId{" +
                "course=" + course +
                ", cdio=" + cdio +
                '}';
    }

    public Course_has_CdioId(Course course, CDIO cdio) {
        this.course = course;
        this.cdio = cdio;
    }

    public Course_has_CdioId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course_has_CdioId that = (Course_has_CdioId) o;
        return Objects.equal(course, that.course) && Objects.equal(cdio, that.cdio);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(course, cdio);
    }
}