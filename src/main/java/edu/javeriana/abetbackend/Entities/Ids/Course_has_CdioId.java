package edu.javeriana.abetbackend.Entities.Ids;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Course_has_CdioId implements Serializable {
    @Serial
    private static final long serialVersionUID = 9221266263378821083L;

    @Column(name = "id_sae")
    private Integer courseId;
    @Column(name = "cdio_number")
    private Float cdioNumber;

    public Course_has_CdioId(Integer courseId, Float cdioNumber) {
        this.courseId = courseId;
        this.cdioNumber = cdioNumber;
    }

    public Course_has_CdioId() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Float getCdioNumber() {
        return cdioNumber;
    }

    public void setCdioNumber(Float cdioNumber) {
        this.cdioNumber = cdioNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course_has_CdioId that = (Course_has_CdioId) o;

        if (!Objects.equals(courseId, that.courseId)) return false;
        return Objects.equals(cdioNumber, that.cdioNumber);
    }

    @Override
    public int hashCode() {
        int result = courseId != null ? courseId.hashCode() : 0;
        result = 31 * result + (cdioNumber != null ? cdioNumber.hashCode() : 0);
        return result;
    }
}