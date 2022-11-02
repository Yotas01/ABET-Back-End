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

    @Column(name = "class_number")
    private Integer classNumber;
    @Column(name = "semester")
    private Integer semester;
    @Column(name = "at_category")
    private String atCategory;
    @Column(name = "exemplary")
    private Integer exemplary;
    @Column(name = "competent")
    private Integer competent;
    @Column(name = "below")
    private Integer below;

    public SectionReport(Long id, Integer classNumber, Integer semester, String atCategory, Integer exemplary, Integer competent, Integer below) {
        this.id = id;
        this.classNumber = classNumber;
        this.semester = semester;
        this.atCategory = atCategory;
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

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getAtCategory() {
        return atCategory;
    }

    public void setAtCategory(String atCategory) {
        this.atCategory = atCategory;
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
