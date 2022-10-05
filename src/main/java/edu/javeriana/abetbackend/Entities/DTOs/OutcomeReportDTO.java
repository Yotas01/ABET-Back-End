package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.Views.CDIOSummary;
import edu.javeriana.abetbackend.Entities.Views.OutcomeSummary;

import java.util.ArrayList;
import java.util.List;

public class OutcomeReportDTO {

    private OutcomeSummary outcomeSummary;
    private List<CDIOSummary> cdioSummaries;

    public OutcomeReportDTO(OutcomeSummary outcomeSummary) {
        this.outcomeSummary = outcomeSummary;
        this.cdioSummaries = new ArrayList<>();
    }

    public OutcomeReportDTO() {
        this.cdioSummaries = new ArrayList<>();
    }

    public OutcomeSummary getOutcomeSummary() {
        return outcomeSummary;
    }

    public void setOutcomeSummary(OutcomeSummary outcomeSummary) {
        this.outcomeSummary = outcomeSummary;
    }

    public List<CDIOSummary> getCdioSummaries() {
        return cdioSummaries;
    }

    public void setCdioSummaries(List<CDIOSummary> cdioSummaries) {
        this.cdioSummaries = cdioSummaries;
    }

    public void addCDIOSummary(CDIOSummary cdioSummary){
        this.cdioSummaries.add(cdioSummary);
    }

    public void removeCDIOSummary(CDIOSummary cdioSummary){
        this.cdioSummaries.remove(cdioSummary);
    }
}
