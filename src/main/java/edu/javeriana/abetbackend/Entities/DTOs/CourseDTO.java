package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDTO {

    private Long courseId;
    private Integer number;
    private String name;
    private Map<Integer,String> sections;
    private Map<Long,String> RAEs;
    private List<Float> cdioList;

    public CourseDTO(Course course) {
        this.courseId = course.getCourseId();
        this.number = course.getNumber();
        this.name = course.getName();
        this.sections =  new HashMap<>();
        course.getSections().forEach(section -> sections.put(section.getNumber(), section.getProfessor()));
        this.RAEs = new HashMap<>();
        course.getRAEs().forEach(rae -> RAEs.put(rae.getRAEId(), rae.getDescription()));
        this.cdioList = new ArrayList<>();
        course.getCdioList().forEach(cdio -> cdioList.add(cdio.getNumber()));
    }

    public CourseDTO() {
        this.cdioList = new ArrayList<>();
        this.sections =  new HashMap<>();
        this.RAEs = new HashMap<>();
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, String> getSections() {
        return sections;
    }

    public void setSections(Map<Integer, String> sections) {
        this.sections = sections;
    }

    public Map<Long, String> getRAEs() {
        return RAEs;
    }

    public void setRAEs(Map<Long, String> RAEs) {
        this.RAEs = RAEs;
    }

    public List<Float> getCdioList() {
        return cdioList;
    }

    public void setCdioList(List<Float> cdioList) {
        this.cdioList = cdioList;
    }
}
