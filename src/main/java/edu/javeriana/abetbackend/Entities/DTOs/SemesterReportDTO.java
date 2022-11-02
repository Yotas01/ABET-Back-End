package edu.javeriana.abetbackend.Entities.DTOs;


import edu.javeriana.abetbackend.Entities.SemesterReport;

public class SemesterReportDTO {

    private Integer semester;
    private Integer outcome;
    private String performance;
    private String improvementActions;

    public SemesterReportDTO(SemesterReport semesterReport){
        this.semester = semesterReport.getSemester();
        this.outcome = semesterReport.getOutcome().getOutcomeId();
        this.performance = semesterReport.getPerformance();
        this.improvementActions = semesterReport.getImprovementActions();
    }

    public SemesterReportDTO(){
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getOutcome() {
        return outcome;
    }

    public void setOutcome(Integer outcome) {
        this.outcome = outcome;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getImprovementActions() {
        return improvementActions;
    }

    public void setImprovementActions(String improvementActions) {
        this.improvementActions = improvementActions;
    }
}
