package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import edu.javeriana.abetbackend.Entities.DTOs.PerformanceIndicatorDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERFORMANCE_INDICATOR")
public class PerformanceIndicator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id_performanceindicator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_rae", referencedColumnName = "id_rae")
    private RAE rae;

    @Column(name = "description")
    private String description;
    @Column(name = "percentage")
    private Double percentage;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "AT_HAS_PI",
            joinColumns = @JoinColumn(name = "id_performanceindicator"),
            inverseJoinColumns = @JoinColumn(name = "id_assessment_tool")
    )
    private List<AssessmentTool> assessmentTools;

    public PerformanceIndicator(Long id, RAE rae, String description, Double percentage) {
        this.id = id;
        this.rae = rae;
        this.description = description;
        this.percentage = percentage;
        this.assessmentTools = new ArrayList<>();
    }

    public PerformanceIndicator() {
        this.assessmentTools = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RAE getRae() {
        return rae;
    }

    public void setRae(RAE rae) {
        this.rae = rae;
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

    public List<AssessmentTool> getAssessmentTools() {
        return assessmentTools;
    }

    public void setAssessmentTools(List<AssessmentTool> assessmentTools) {
        this.assessmentTools = assessmentTools;
    }

    public void addAssessmentTool(AssessmentTool assessmentTool){
        this.assessmentTools.add(assessmentTool);
    }

    public void removeAssessmentTool(AssessmentTool assessmentTool){
        this.assessmentTools.remove(assessmentTool);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PerformanceIndicator that)) return false;
        return Objects.equal(id, that.id) && Objects.equal(rae, that.rae) && Objects.equal(description, that.description) && Objects.equal(percentage, that.percentage) && Objects.equal(assessmentTools, that.assessmentTools);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, rae, description, percentage, assessmentTools);
    }
}