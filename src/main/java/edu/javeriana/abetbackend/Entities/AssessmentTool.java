package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class AssessmentTool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idAssessmentTool")
    private Long AssessmentToolId;
    @Basic
    private String description;
    @Basic
    private Double value;
    @ManyToOne
    @JoinColumn(name = "idRAE")
    private RAE rae;
    @OneToMany(mappedBy = "assessmentTool", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PerformanceIndicator> performanceIndicators;
    @OneToOne(mappedBy = "assessmentTool")
    private SectionAssessmentTool sectionAssessmentTool;

    public AssessmentTool() {
        this.performanceIndicators = new ArrayList<>();
    }

    public AssessmentTool(Long assessmentToolId, String description, Double value, RAE rae) {
        AssessmentToolId = assessmentToolId;
        this.description = description;
        this.value = value;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
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
        return Objects.equal(AssessmentToolId, that.AssessmentToolId) && Objects.equal(description, that.description) && Objects.equal(value, that.value) && Objects.equal(rae, that.rae) && Objects.equal(performanceIndicators, that.performanceIndicators);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(AssessmentToolId, description, value, rae);
    }
}