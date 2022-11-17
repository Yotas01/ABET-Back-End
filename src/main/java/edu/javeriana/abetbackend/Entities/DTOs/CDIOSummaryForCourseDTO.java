package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.Views.CDIOSummaryForCourse;

public class CDIOSummaryForCourseDTO {

    private Integer courseNumber;
    private String courseName;
    private Float cdioNumber;
    private Double exemplary;
    private Double competent;
    private Double below;

    public CDIOSummaryForCourseDTO(CDIOSummaryForCourse summary) {
        this.courseNumber = summary.getCourse().getNumber();
        this.courseName = summary.getCourse().getName();
        this.cdioNumber = summary.getCdio().getNumber();
        this.exemplary = summary.getExemplary();
        this.competent = summary.getCompetent();
        this.below = summary.getBelow();
    }

    public CDIOSummaryForCourseDTO() {
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Float getCdioNumber() {
        return cdioNumber;
    }

    public void setCdioNumber(Float cdioNumber) {
        this.cdioNumber = cdioNumber;
    }

    public Double getExemplary() {
        return exemplary;
    }

    public void setExemplary(Double exemplary) {
        this.exemplary = exemplary;
    }

    public Double getCompetent() {
        return competent;
    }

    public void setCompetent(Double competent) {
        this.competent = competent;
    }

    public Double getBelow() {
        return below;
    }

    public void setBelow(Double below) {
        this.below = below;
    }
}
