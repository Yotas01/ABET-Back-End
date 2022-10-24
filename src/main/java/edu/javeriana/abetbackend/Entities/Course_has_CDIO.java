package edu.javeriana.abetbackend.Entities;

import edu.javeriana.abetbackend.Entities.Ids.Course_has_CdioId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "COURSE_HAS_CDIO")
public class Course_has_CDIO {

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

    @Column(name = "bloom_value")
    private Integer bloomValue;

    public Course_has_CDIO() {
    }

    public Course_has_CDIO(Course_has_CdioId id, Course course, CDIO cdio, Integer bloomValue) {
        this.id = id;
        this.course = course;
        this.cdio = cdio;
        this.bloomValue = bloomValue;
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

    public Course_has_CdioId getId() {
        return id;
    }

    public void setId(Course_has_CdioId id) {
        this.id = id;
    }

    public Integer getBloomValue() {
        return bloomValue;
    }

    public void setBloomValue(Integer bloomValue) {
        this.bloomValue = bloomValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course_has_CDIO that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(course, that.course)) return false;
        if (!Objects.equals(cdio, that.cdio)) return false;
        return Objects.equals(bloomValue, that.bloomValue);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (cdio != null ? cdio.hashCode() : 0);
        result = 31 * result + (bloomValue != null ? bloomValue.hashCode() : 0);
        return result;
    }
}
