package edu.javeriana.abetbackend.Entities.Views;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "ASSESSMENT_SUMMARY")
public class AssessmentSummary {

    @Id
    @Column(name = "id_section")
    private Long idSection;
    @Basic
    @Column(name = "id_rae")
    private Long idRae;
    @Basic
    @Column(name = "id_assessment_tool")
    private Long idAssessmentTool;
    @Basic
    private Double value;
    @Basic
    private Double exemplary;
    @Basic
    private Double competent;
    @Basic
    private Double below;

    public AssessmentSummary(Long idSection, Long idRae, Long idAssessmentTool, Double value, Double exemplary, Double competent, Double below) {
        this.idSection = idSection;
        this.idRae = idRae;
        this.idAssessmentTool = idAssessmentTool;
        this.value = value;
        this.exemplary = exemplary;
        this.competent = competent;
        this.below = below;
    }

    public AssessmentSummary() {
    }

    public Long getIdSection() {
        return idSection;
    }

    public void setIdSection(Long idSection) {
        this.idSection = idSection;
    }

    public Long getIdRae() {
        return idRae;
    }

    public void setIdRae(Long idRae) {
        this.idRae = idRae;
    }

    public Long getIdAssessmentTool() {
        return idAssessmentTool;
    }

    public void setIdAssessmentTool(Long idAssessmentTool) {
        this.idAssessmentTool = idAssessmentTool;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
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
