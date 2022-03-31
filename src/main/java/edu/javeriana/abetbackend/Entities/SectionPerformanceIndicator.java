package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "section_performanceindicator")
public class SectionPerformanceIndicator {

    @Id
    @Column(name = "section_performanceIndicatorId")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long sectionPerformanceIndicatorId;
    @ManyToOne
    @JoinColumn(name = "section_assessmentToolId")
    private SectionAssessmentTool sectionAssessmentTool;
    @OneToOne
    @JoinColumn(name = "idPerformanceIndicator", referencedColumnName = "idPerformanceIndicator")
    private PerformanceIndicator performanceIndicator;
    @Basic
    private Integer exemplary;
    @Basic
    private Integer competent;
    @Basic
    private Integer below;
    @Basic
    private int draft;

    public SectionPerformanceIndicator() {
    }

    public Long getSectionPerformanceIndicatorId() {
        return sectionPerformanceIndicatorId;
    }

    public void setSectionPerformanceIndicatorId(Long sectionPerformanceIndicatorId) {
        this.sectionPerformanceIndicatorId = sectionPerformanceIndicatorId;
    }

    public SectionAssessmentTool getSectionAssessmentTool() {
        return sectionAssessmentTool;
    }

    public void setSectionAssessmentTool(SectionAssessmentTool sectionAssessmentTool) {
        this.sectionAssessmentTool = sectionAssessmentTool;
    }

    public PerformanceIndicator getPerformanceIndicator() {
        return performanceIndicator;
    }

    public void setPerformanceIndicator(PerformanceIndicator performanceIndicator) {
        this.performanceIndicator = performanceIndicator;
    }

    public Integer getExemplary() {
        return exemplary;
    }

    public void setExemplary(Integer exemplary) {
        this.exemplary = exemplary;
    }

    public Integer getCompetent() {
        return competent;
    }

    public void setCompetent(Integer competent) {
        this.competent = competent;
    }

    public Integer getBelow() {
        return below;
    }

    public void setBelow(Integer below) {
        this.below = below;
    }

    public int getDraft() {
        return draft;
    }

    public void setDraft(int draft) {
        this.draft = draft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionPerformanceIndicator that = (SectionPerformanceIndicator) o;
        return draft == that.draft && Objects.equal(sectionPerformanceIndicatorId, that.sectionPerformanceIndicatorId) && Objects.equal(exemplary, that.exemplary) && Objects.equal(competent, that.competent) && Objects.equal(below, that.below);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sectionPerformanceIndicatorId, exemplary, competent, below, draft);
    }
}
