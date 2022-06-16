package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.Views.CDIOReport;
import edu.javeriana.abetbackend.Entities.Views.OutcomeReport;

import java.util.List;

public class OutcomeReportDTO {
    private OutcomeReport outcomeReport;
    private List<CDIOReport> cdioReports;

    public OutcomeReportDTO(OutcomeReport outcomeReport, List<CDIOReport> cdioReports) {
        this.outcomeReport = outcomeReport;
        this.cdioReports = cdioReports;
    }

    public OutcomeReportDTO() {
    }

    public OutcomeReport getOutcomeReport() {
        return outcomeReport;
    }

    public void setOutcomeReport(OutcomeReport outcomeReport) {
        this.outcomeReport = outcomeReport;
    }

    public List<CDIOReport> getCdioReports() {
        return cdioReports;
    }

    public void setCdioReports(List<CDIOReport> cdioReports) {
        this.cdioReports = cdioReports;
    }
}
