package edu.javeriana.abetbackend.Entities.ResponseEntities;

import edu.javeriana.abetbackend.Entities.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseCourse {

    private Long CourseId;
    private Integer number;
    private String name;
    private Map<Integer,String> sections;
    private List<String> RAEs;
    private List<Float> cdioList;

    public ResponseCourse(Course course) {
        this.CourseId = course.getCourseId();
        this.number = course.getNumber();
        this.name = course.getName();
        this.sections =  new HashMap<>();
        course.getSections().forEach(section -> sections.put(section.getNumber(), section.getProfessor()));
        this.RAEs = new ArrayList<>();
        course.getRAEs().forEach(rae -> RAEs.add(rae.getDescription()));
        this.cdioList = new ArrayList<>();
        course.getCdioList().forEach(cdio -> cdioList.add(cdio.getNumber()));
    }

    public ResponseCourse() {
        this.cdioList = new ArrayList<>();
        this.sections =  new HashMap<>();
        this.RAEs = new ArrayList<>();
    }

    public Long getCourseId() {
        return CourseId;
    }

    public void setCourseId(Long courseId) {
        CourseId = courseId;
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

    public List<String> getRAEs() {
        return RAEs;
    }

    public void setRAEs(List<String> RAEs) {
        this.RAEs = RAEs;
    }

    public List<Float> getCdioList() {
        return cdioList;
    }

    public void setCdioList(List<Float> cdioList) {
        this.cdioList = cdioList;
    }
}
