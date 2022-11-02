package edu.javeriana.abetbackend.Entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "SECTION_REPORT")
public class SectionReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id_section_report")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_section", referencedColumnName = "id_section")
    private Section section;

    @Column(name = "at_category")
    private String atCategory;
    @Column(name = "exemplary")
    private Integer exemplary;
    @Column(name = "competent")
    private Integer competent;
    @Column(name = "below")
    private Integer below;

    public SectionReport(Long id, Section section, String ATCode, Integer exemplary, Integer competent, Integer below) {
        this.id = id;
        this.section = section;
        this.atCategory = ATCode;
        this.exemplary = exemplary;
        this.competent = competent;
        this.below = below;
    }

    public SectionReport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getAtCategory() {
        return atCategory;
    }

    public void setAtCategory(String ATCode) {
        this.atCategory = ATCode;
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
}
