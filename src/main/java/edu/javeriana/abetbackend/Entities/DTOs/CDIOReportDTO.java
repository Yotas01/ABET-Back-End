package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.Views.CDIOReport;
import edu.javeriana.abetbackend.Entities.Views.CourseReport;

import java.util.List;

public class CDIOReportDTO {

    private CDIOReport cdioReport;
    private List<CourseReport> courseReports;

    public CDIOReportDTO(CDIOReport cdioReport, List<CourseReport> courseReports) {
        this.cdioReport = cdioReport;
        this.courseReports = courseReports;
    }

    public CDIOReportDTO() {
    }

    public CDIOReport getCdioReport() {
        return cdioReport;
    }

    public void setCdioReport(CDIOReport cdioReport) {
        this.cdioReport = cdioReport;
    }

    public List<CourseReport> getCourseReports() {
        return courseReports;
    }

    public void setCourseReports(List<CourseReport> courseReports) {
        this.courseReports = courseReports;
    }
}
