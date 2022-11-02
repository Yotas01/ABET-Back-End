package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.AssessmentTool;

import java.util.ArrayList;
import java.util.List;

public class AssessmentToolDTO {

    private Long id;
    private String category;
    private Integer courseId;
    private Integer semester;
    private String description;
    private Double value;
    private List<PerformanceIndicatorDTO> performanceIndicators;

    public AssessmentToolDTO(AssessmentTool assessmentTool){
        this.id = assessmentTool.getAssessmentToolId();
        this.category = assessmentTool.getCategory();
        this.courseId = assessmentTool.getCourse().getCourseId();
        this.semester = assessmentTool.getSemester();
        this.description = assessmentTool.getDescription();
        this.value = assessmentTool.getValue();
        this.performanceIndicators = new ArrayList<>();
        if(assessmentTool.getPerformanceIndicators() != null && !assessmentTool.getPerformanceIndicators().isEmpty())
            assessmentTool.getPerformanceIndicators().forEach(pi -> this.performanceIndicators.add(new PerformanceIndicatorDTO(pi)));

    }

    public AssessmentToolDTO(Long id, String category, Integer course_id, Integer semester, String description, Double value) {
        this.id = id;
        this.category = category;
        this.courseId = course_id;
        this.semester = semester;
        this.description = description;
        this.value = value;
        this.performanceIndicators = new ArrayList<>();
    }

    public AssessmentToolDTO() {
        this.performanceIndicators = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String code) {
        this.category = code;
    }

    public Integer getCourse_id() {
        return courseId;
    }

    public void setCourse_id(Integer course_id) {
        this.courseId = course_id;
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

    public List<PerformanceIndicatorDTO> getPerformanceIndicators() {
        return performanceIndicators;
    }

    public void setPerformanceIndicators(List<PerformanceIndicatorDTO> performanceIndicators) {
        this.performanceIndicators = performanceIndicators;
    }
}
