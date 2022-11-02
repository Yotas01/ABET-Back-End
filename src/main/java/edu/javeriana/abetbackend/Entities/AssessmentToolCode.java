package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ASSESSMENT_TOOL_CODE")
public class AssessmentToolCode {

    @Id
    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "code")
    private List<AssessmentTool> assessmentTools;

    public AssessmentToolCode(String code) {
        this.code = code;
    }

    public AssessmentToolCode() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssessmentToolCode that)) return false;
        return Objects.equal(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
