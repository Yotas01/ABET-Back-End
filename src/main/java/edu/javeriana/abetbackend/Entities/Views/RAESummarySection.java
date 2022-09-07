package edu.javeriana.abetbackend.Entities.Views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table(name = "RAE_SUMMARY_SECTION")
@Immutable
public class RAESummarySection {

    @Id
    @Column(name = "id_section")
    private Long sectionId;
    @Basic
    @Column(name = "id_rae")
    private Long raeId;
    @Basic
    private Double exemplary;
    @Basic
    private Double competent;
    @Basic
    private Double below;

    public RAESummarySection(Long sectionId, Long raeId, Double exemplary, Double competent, Double below) {
        this.sectionId = sectionId;
        this.raeId = raeId;
        this.exemplary = exemplary;
        this.competent = competent;
        this.below = below;
    }

    public RAESummarySection() {
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getRaeId() {
        return raeId;
    }

    public void setRaeId(Long raeId) {
        this.raeId = raeId;
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
