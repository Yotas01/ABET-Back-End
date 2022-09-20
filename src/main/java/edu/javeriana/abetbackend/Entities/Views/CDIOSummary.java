package edu.javeriana.abetbackend.Entities.Views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "CDIO_SUMMARY")
public class CDIOSummary {

    @Id
    @Column(name = "cdio_number")
    private Float cdioNumber;
    @Basic
    @Column(name = "exemplary")
    private Double exemplary;
    @Basic
    @Column(name = "competent")
    private Double competent;
    @Basic
    @Column(name = "below")
    private Double below;

    public CDIOSummary(Float cdioNumber, Double exemplary, Double competent, Double below) {
        this.cdioNumber = cdioNumber;
        this.exemplary = exemplary;
        this.competent = competent;
        this.below = below;
    }

    public CDIOSummary() {
    }

    public Float getCdioNumber() {
        return cdioNumber;
    }

    public void setCdioNumber(Float cdioNumber) {
        this.cdioNumber = cdioNumber;
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
