package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.Views.CourseReport;
import edu.javeriana.abetbackend.Entities.Views.SectionReport;

import java.util.List;

public class CourseReportDTO {

    private CourseReport courseReport;
    private List<SectionReport> sectionsReport;

    public CourseReportDTO(CourseReport courseReport, List<SectionReport> sectionsReport) {
        this.courseReport = courseReport;
        this.sectionsReport = sectionsReport;
    }

    public CourseReportDTO() {
    }

    public CourseReport getCourseReport() {
        return courseReport;
    }

    public void setCourseReport(CourseReport courseReport) {
        this.courseReport = courseReport;
    }

    public List<SectionReport> getSectionsReport() {
        return sectionsReport;
    }

    public void setSectionsReport(List<SectionReport> sectionsReport) {
        this.sectionsReport = sectionsReport;
    }
}
