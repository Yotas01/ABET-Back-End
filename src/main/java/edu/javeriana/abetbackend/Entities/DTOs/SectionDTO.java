package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.Section;

import java.util.HashMap;
import java.util.Map;

public class SectionDTO {

    private Long sectionId;
    private Integer classNumber;
    private Integer semester;
    private String professor;
    private Integer totalStudents;
    private Map<Integer, String> course;

    public SectionDTO() {
        this.course = new HashMap<>();
    }

    public SectionDTO(Section section) {
        this.sectionId = section.getSectionId();
        this.classNumber = section.getClassNumber();
        this.professor = section.getProfessor();
        this.totalStudents = section.getTotalStudents();
        this.semester = section.getSemester();
        this.course = new HashMap<>();
        course.put(section.getCourse().getCourseId(),section.getCourse().getName());
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
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

    public Map<Integer, String> getCourse() {
        return course;
    }

    public void setCourse(Map<Integer, String> course) {
        this.course = course;
    }
}
