package edu.javeriana.abetbackend.Entities.DTOs;


import edu.javeriana.abetbackend.Entities.Views.CDIOSummaryForCourse;

import java.util.List;

public class CourseReportDTO {

    private List<RAESummaryDTO> raeSummaries;
    private List<CDIOSummaryForCourse> cdioSummary;

    public CourseReportDTO(List<RAESummaryDTO> raeSummaries, List<CDIOSummaryForCourse> cdioSummary) {
        this.raeSummaries = raeSummaries;
        this.cdioSummary = cdioSummary;
    }

    public CourseReportDTO() {
    }

    public List<RAESummaryDTO> getRaeSummaries() {
        return raeSummaries;
    }

    public void setRaeSummaries(List<RAESummaryDTO> raeSummaries) {
        this.raeSummaries = raeSummaries;
    }

    public List<CDIOSummaryForCourse> getCdioSummary() {
        return cdioSummary;
    }

    public void setCdioSummary(List<CDIOSummaryForCourse> cdioSummary) {
        this.cdioSummary = cdioSummary;
    }
}
