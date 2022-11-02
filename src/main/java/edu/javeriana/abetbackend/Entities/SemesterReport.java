package edu.javeriana.abetbackend.Entities;


import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "SEMESTER_REPORT")
public class SemesterReport {

    @Id
    @Column(name = "semester")
    private Integer semester;

    @ManyToOne
    @JoinColumn(name = "id_outcome", referencedColumnName = "id_outcome")
    private Outcome outcome;

    @Basic
    @Column(name = "performance")
    private String performance;
    @Basic
    @Column(name = "improvement_actions")
    private String improvementActions;

    public SemesterReport(Integer semester, Outcome outcome, String performance, String improvementActions) {
        this.semester = semester;
        this.outcome = outcome;
        this.performance = performance;
        this.improvementActions = improvementActions;
    }

    public SemesterReport() {
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SemesterReport report)) return false;
        return Objects.equal(semester, report.semester) && Objects.equal(outcome, report.outcome) && Objects.equal(performance, report.performance) && Objects.equal(improvementActions, report.improvementActions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(semester, outcome, performance, improvementActions);
    }
}
