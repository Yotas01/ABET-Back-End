package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.Section;

import java.util.HashMap;
import java.util.Map;

public class SectionDTO {

    private Long sectionId;
    private Integer number;
    private String professor;
    private Integer totalStudents;
    private Integer semester;
    private Map<Integer, String> course;

    public SectionDTO() {
        this.course = new HashMap<>();
    }

    public SectionDTO(Section section) {
        this.sectionId = section.getSectionId();
        this.number = section.getNumber();
        this.professor = section.getProfessor();
        this.totalStudents = section.getTotalStudents();
        this.semester = section.getSemester();
        this.course = new HashMap<>();
        course.put(section.getCourse().getNumber(),section.getCourse().getName());
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Map<Integer, String> getCourse() {
        return course;
    }

    public void setCourse(Map<Integer, String> course) {
        this.course = course;
    }
}
