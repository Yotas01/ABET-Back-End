package edu.javeriana.abetbackend.Entities.Ids;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

public class SectionId implements Serializable {

    @Column(name = "class_number")
    public Integer classNumber;
    @Column(name = "semester")
    public Integer semester;

    public SectionId() {
    }

    public SectionId(Integer classNumber, Integer semester) {
        this.classNumber = classNumber;
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SectionId sectionId)) return false;
        return Objects.equal(classNumber, sectionId.classNumber) && Objects.equal(semester, sectionId.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(classNumber, semester);
    }
}
