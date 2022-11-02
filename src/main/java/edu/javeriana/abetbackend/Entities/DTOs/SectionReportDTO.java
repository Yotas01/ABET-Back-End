package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.SectionReport;

import java.util.HashMap;
import java.util.Map;

public class SectionReportDTO {

    private Long id;
    private Map<Integer, String> section;
    private String atCode;
    private Integer exemplary;
    private Integer competent;
    private Integer below;

    public SectionReportDTO(SectionReport sectionReport) {
        this.id = sectionReport.getId();
        this.section = new HashMap<>();
        this.section.put(sectionReport.getSection().getClassNumber(), sectionReport.getSection().getProfessor());
        this.atCode = sectionReport.getAtCategory();
        this.exemplary = sectionReport.getExemplary();
        this.competent = sectionReport.getCompetent();
        this.below = sectionReport.getBelow();
    }

    public SectionReportDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Integer, String> getSection() {
        return section;
    }

    public void setSection(Map<Integer, String> section) {
        this.section = section;
    }

    public String getAtCode() {
        return atCode;
    }

    public void setAtCode(String atCode) {
        this.atCode = atCode;
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
