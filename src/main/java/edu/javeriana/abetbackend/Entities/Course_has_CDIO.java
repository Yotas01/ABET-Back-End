package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import edu.javeriana.abetbackend.Entities.Ids.Course_has_CdioId;

import javax.persistence.*;

@Entity
@Table(name = "course_has_cdio")
public class Course_has_CDIO {

    @EmbeddedId
    private Course_has_CdioId id;

    @Basic
    private Integer value;

    public Course_has_CDIO() {
    }

    public Course_has_CDIO(Course_has_CdioId id, Integer value) {
        this.id = id;
        this.value = value;
    }

    public Course_has_CdioId getId() {
        return id;
    }

    public void setId(Course_has_CdioId id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course_has_CDIO that = (Course_has_CDIO) o;
        return Objects.equal(id, that.id) && Objects.equal(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, value);
    }
}
