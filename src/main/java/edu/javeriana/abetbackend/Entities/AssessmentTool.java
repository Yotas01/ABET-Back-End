package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "ASSESSMENT_TOOL")
public class AssessmentTool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id_assessment_tool")
    private Long assessmentToolId;

    @ManyToOne
    @JoinColumn(name = "code", referencedColumnName = "code")
    private AssessmentToolCode code;
    @ManyToOne
    @JoinColumn(name = "course_id_sae", referencedColumnName = "id_sae")
    private Course course;

    @Column(name = "semester")
    private Integer semester;
    @Column(name = "description")
    private String description;
    @Column(name = "value")
    private Double value;

    @ManyToMany(mappedBy = "assessmentTools")
    private List<PerformanceIndicator> performanceIndicators;

    public AssessmentTool(Long assessmentToolId, AssessmentToolCode code, Course course, Integer semester, String description, Double value) {
        this.assessmentToolId = assessmentToolId;
        this.code = code;
        this.course = course;
        this.semester = semester;
        this.description = description;
        this.value = value;
        this.performanceIndicators = new ArrayList<>();
    }

    public AssessmentTool() {
        this.performanceIndicators = new ArrayList<>();
    }

    public Long getAssessmentToolId() {
        return assessmentToolId;
    }

    public void setAssessmentToolId(Long assessmentToolId) {
        this.assessmentToolId = assessmentToolId;
    }

    public AssessmentToolCode getCode() {
        return code;
    }

    public void setCode(AssessmentToolCode code) {
        this.code = code;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
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
        return performanceIndicators;
    }

    public void setPerformanceIndicators(List<PerformanceIndicator> performanceIndicators) {
        this.performanceIndicators = performanceIndicators;
    }

    public void addPerformanceIndicator(PerformanceIndicator performanceIndicator){
        this.performanceIndicators.add(performanceIndicator);
    }

    public void removePerformanceIndicator(PerformanceIndicator performanceIndicator){
        this.performanceIndicators.remove(performanceIndicator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssessmentTool that)) return false;
        return Objects.equal(assessmentToolId, that.assessmentToolId) && Objects.equal(code, that.code) && Objects.equal(course, that.course) && Objects.equal(semester, that.semester) && Objects.equal(description, that.description) && Objects.equal(value, that.value) && Objects.equal(performanceIndicators, that.performanceIndicators);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(assessmentToolId, code, course, semester, description, value, performanceIndicators);
    }
}