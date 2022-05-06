package edu.javeriana.abetbackend.Entities.Views;

import org.hibernate.annotations.Immutable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
public class OutcomeReport {

    @Id
    @Column(name = "idOutcome")
    private Integer outcomeId;
    @Basic
    private Integer semester;
    @Basic
    private Double exemplary;
    @Basic
    private Double competent;
    @Basic
    private Double below;

    public OutcomeReport() {
    }

    public Integer getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Integer outcomeId) {
        this.outcomeId = outcomeId;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Double getExemplary() {
        return exemplary;
    }

    public void setExemplary(Double exemplary) {
        this.exemplary = exemplary;
    }

    public Double getCompetent() {
        return competent;
    }

    public void setCompetent(Double competent) {
        this.competent = competent;
    }

    public Double getBelow() {
        return below;
    }

    public void setBelow(Double below) {
        this.below = below;
    }
}
