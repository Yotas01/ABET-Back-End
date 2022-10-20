package edu.javeriana.abetbackend.Entities;

import javax.persistence.*;

@Entity
@Table(name = "SEMESTER_REPORT")
public class SemesterReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "semester")
    private Integer semester;
    @Basic
    @Column(name = "performance")
    private String performance;
    @Basic
    @Column(name = "improvement_actions")
    private String improvementActions;

    public SemesterReport(Long id, Integer semester, String performance, String improvementActions) {
        this.id = id;
        this.semester = semester;
        this.performance = performance;
        this.improvementActions = improvementActions;
    }

    public SemesterReport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
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
