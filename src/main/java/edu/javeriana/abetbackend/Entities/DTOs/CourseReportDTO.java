package edu.javeriana.abetbackend.Entities.DTOs;


import edu.javeriana.abetbackend.Entities.Views.CDIOSummaryForCourse;

import java.util.ArrayList;
import java.util.List;

public class CourseReportDTO {

    private List<RAESummaryDTO> raeSummaries;
    private List<CDIOSummaryForCourseDTO> cdioSummary;
    private List<String> comments;

    public CourseReportDTO(List<RAESummaryDTO> raeSummaries, List<CDIOSummaryForCourse> cdioSummary) {
        this.raeSummaries = raeSummaries;
        this.cdioSummary = new ArrayList<>();
        cdioSummary.forEach(summary -> this.cdioSummary.add(new CDIOSummaryForCourseDTO(summary)));
        this.comments = new ArrayList<>();
    }

    public CourseReportDTO() {
        this.cdioSummary = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public List<RAESummaryDTO> getRaeSummaries() {
        return raeSummaries;
    }

    public void setRaeSummaries(List<RAESummaryDTO> raeSummaries) {
        this.raeSummaries = raeSummaries;
    }

    public List<CDIOSummaryForCourseDTO> getCdioSummary() {
        return cdioSummary;
    }

    public void setCdioSummary(List<CDIOSummaryForCourseDTO> cdioSummary) {
        this.cdioSummary = cdioSummary;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public void addComment(String comment){
        this.comments.add(comment);
    }

    public void removeComment(String comment){
        this.comments.remove(comment);
    }
}
