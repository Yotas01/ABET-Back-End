package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class AssessmentTool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idAssessmentTool")
    private Long AssessmentToolId;
    @Basic
    private String description;
    @Basic
    private Integer value;
    @Basic
    private Integer totalStudents;
    @ManyToOne
    @JoinColumn(name = "idRAE")
    private RAE rae;
    @OneToMany(mappedBy = "assessmentTool")
    private List<PerformanceIndicator> performanceIndicators;

    public AssessmentTool() {
        this.performanceIndicators = new ArrayList<>();
    }

    public AssessmentTool(Long assessmentToolId, String description, Integer value, Integer totalStudents, RAE rae) {
        AssessmentToolId = assessmentToolId;
        this.description = description;
        this.value = value;
        this.totalStudents = totalStudents;
        this.rae = rae;
        this.performanceIndicators = new ArrayList<>();
    }

    public RAE getRae() {
        return rae;
    }

    public void setRae(RAE rae) {
        this.rae = rae;
    }

    public Long getAssessmentToolId() {
        return AssessmentToolId;
    }

    public void setAssessmentToolId(Long AssessmentToolId) {
        this.AssessmentToolId = AssessmentToolId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public List<PerformanceIndicator> getPerformanceIndicators() {
        if (performanceIndicators == null) {
            performanceIndicators = new ArrayList<>();
        }
        return performanceIndicators;
    }

    public void setPerformanceIndicators(List<PerformanceIndicator> performanceIndicators) {
        this.performanceIndicators = performanceIndicators;
    }

    public void addPerformanceIndicator(PerformanceIndicator performanceIndicator) {
        getPerformanceIndicators().add(performanceIndicator);
    }

    public void removePerformanceIndicator(PerformanceIndicator performanceIndicator) {
        getPerformanceIndicators().remove(performanceIndicator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssessmentTool that = (AssessmentTool) o;
        return Objects.equal(AssessmentToolId, that.AssessmentToolId) && Objects.equal(description, that.description) && Objects.equal(value, that.value) && Objects.equal(totalStudents, that.totalStudents) && Objects.equal(rae, that.rae) && Objects.equal(performanceIndicators, that.performanceIndicators);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(AssessmentToolId, description, value, totalStudents, rae, performanceIndicators);
    }
}