package edu.javeriana.abetbackend.Entities.Views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "OUTCOME_SUMMARY")
public class OutcomeSummary {

    @Id
    @Column(name = "outcome")
    private Integer outcomeId;
    @Basic
    @Column(name = "exemplary")
    private Double exemplary;
    @Basic
    @Column(name = "competent")
    private Double competent;
    @Basic
    @Column(name = "below")
    private Double below;

    public OutcomeSummary(Integer outcomeId, Double exemplary, Double competent, Double below) {
        this.outcomeId = outcomeId;
        this.exemplary = exemplary;
        this.competent = competent;
        this.below = below;
    }

    public OutcomeSummary() {
    }

    public Integer getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Integer outcomeId) {
        this.outcomeId = outcomeId;
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
