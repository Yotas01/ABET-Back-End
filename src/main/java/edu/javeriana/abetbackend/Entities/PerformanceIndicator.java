package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "performanceindicator")
public class PerformanceIndicator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idPerformanceIndicator")
    private Long PerformanceIndicatorId;
    @Basic
    private String description;
    @Basic
    private Double percentage;
    @ManyToOne
    @JoinColumn(name = "idAssessmentTool")
    private AssessmentTool assessmentTool;
    @OneToMany(mappedBy = "performanceIndicator", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<SectionPerformanceIndicator> sectionPerformanceIndicators;

    public PerformanceIndicator() {
    }

    public PerformanceIndicator(Long performanceIndicatorId, String description, Double percentage, AssessmentTool assessmentTool) {
        PerformanceIndicatorId = performanceIndicatorId;
        this.description = description;
        this.percentage = percentage;
        this.assessmentTool = assessmentTool;
    }

    public AssessmentTool getAssessmentTool() {
        return assessmentTool;
    }

    public void setAssessmentTool(AssessmentTool assessmentTool) {
        this.assessmentTool = assessmentTool;
    }

    public Long getPerformanceIndicatorId() {
        return PerformanceIndicatorId;
    }

    public void setPerformanceIndicatorId(Long PerformanceIndicatorId) {
        this.PerformanceIndicatorId = PerformanceIndicatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerformanceIndicator that = (PerformanceIndicator) o;
        return Objects.equal(PerformanceIndicatorId, that.PerformanceIndicatorId) && Objects.equal(description, that.description) && Objects.equal(percentage, that.percentage) && Objects.equal(assessmentTool, that.assessmentTool);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(PerformanceIndicatorId, description, percentage);
    }
}