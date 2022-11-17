package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.Views.CDIOSummary;
import edu.javeriana.abetbackend.Entities.Views.CDIOSummaryForCourse;

import java.util.ArrayList;
import java.util.List;

public class CDIOReportDTO {

    private CDIOSummary cdioSummary;
    private List<CDIOSummaryForCourseDTO> cdioSummaryForCourseList;

    public CDIOReportDTO(CDIOSummary cdioSummary) {
        this.cdioSummary = cdioSummary;
        this.cdioSummaryForCourseList = new ArrayList<>();
    }

    public CDIOReportDTO() {
        cdioSummaryForCourseList = new ArrayList<>();
    }

    public CDIOSummary getCdioSummary() {
        return cdioSummary;
    }

    public void setCdioSummary(CDIOSummary cdioSummary) {
        this.cdioSummary = cdioSummary;
    }

    public List<CDIOSummaryForCourseDTO> getCdioSummaryForCourseList() {
        return cdioSummaryForCourseList;
    }

    public void setCdioSummaryForCourseList(List<CDIOSummaryForCourseDTO> cdioSummaryForCourseList) {
        this.cdioSummaryForCourseList = cdioSummaryForCourseList;
    }

    public void addCourseSummary(CDIOSummaryForCourse summary){
        this.cdioSummaryForCourseList.add(new CDIOSummaryForCourseDTO(summary));
    }

    public void removeCourseSummary(CDIOSummaryForCourse summary){
        this.cdioSummaryForCourseList.remove(new CDIOSummaryForCourseDTO(summary));
    }
}
