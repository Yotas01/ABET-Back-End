package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import edu.javeriana.abetbackend.Entities.Ids.Course_has_CdioId;

import javax.persistence.*;

@Entity
@Table(name = "COURSE_HAS_CDIO")
public class Course_has_CDIO {

    @EmbeddedId
    private Course_has_CdioId id;

    @Basic
    @Column(name = "bloom_value")
    private Integer bloomValue;

    public Course_has_CDIO() {
    }

    public Course_has_CDIO(Course_has_CdioId id, Integer bloomValue) {
        this.id = id;
        this.bloomValue = bloomValue;
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
        if (o == null || getClass() != o.getClass()) return false;
        Course_has_CDIO that = (Course_has_CDIO) o;
        return Objects.equal(id, that.id) && Objects.equal(bloomValue, that.bloomValue);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, bloomValue);
    }
}
