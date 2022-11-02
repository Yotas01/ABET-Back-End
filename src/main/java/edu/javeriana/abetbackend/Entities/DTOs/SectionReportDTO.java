package edu.javeriana.abetbackend.Entities.DTOs;

public class SectionReportDTO {

    private Long id;
    private Integer classNumber;
    private Integer semester;
    private String atCode;
    private Integer exemplary;
    private Integer competent;
    private Integer below;

    public SectionReportDTO(Long id, Integer classNumber, Integer semester, String atCode, Integer exemplary, Integer competent, Integer below) {
        this.id = id;
        this.classNumber = classNumber;
        this.semester = semester;
        this.atCode = atCode;
        this.exemplary = exemplary;
        this.competent = competent;
        this.below = below;
    }

    public SectionReportDTO() {
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
