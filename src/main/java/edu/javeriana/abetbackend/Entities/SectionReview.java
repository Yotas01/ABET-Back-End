package edu.javeriana.abetbackend.Entities;

import edu.javeriana.abetbackend.Entities.DTOs.SectionAssessmentToolDTO;

import java.util.List;

public class SectionReview {
    private Integer courseNumber;
    private Integer sectionNumber;
    private Integer semester;
    private List<SectionAssessmentToolDTO> sectionAssessmentTools;

    public SectionReview() {
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Integer getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(Integer sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public List<SectionAssessmentToolDTO> getSectionAssessmentTools() {
        return sectionAssessmentTools;
    }

    public void setSectionAssessmentTools(List<SectionAssessmentToolDTO> sectionAssessmentTools) {
        this.sectionAssessmentTools = sectionAssessmentTools;
    }
}
