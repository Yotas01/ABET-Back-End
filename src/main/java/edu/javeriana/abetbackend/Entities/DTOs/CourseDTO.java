package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDTO {

    private Integer courseId;
    private String name;
    private Map<Float, String> cdioList;
    private Map<Integer,String> sections;
    private Map<String, Double> assessmentTools;
    private Map<Long,String> RAEs;


    public CourseDTO(Course course) {
        this.courseId = course.getCourseId();
        this.name = course.getName();

        this.cdioList = new HashMap<>();
        if(!course.getCDIOList().isEmpty())
            course.getCdioList().forEach(cdio -> cdioList.put(cdio.getCdio().getNumber(), cdio.getCdio().getDescription()));
        this.sections =  new HashMap<>();
        if(!course.getSections().isEmpty())
            course.getSections().forEach(section -> sections.put(section.getClassNumber(), section.getProfessor()));
        this.assessmentTools = new HashMap<>();
        if(!course.getAssessmentTools().isEmpty())
            course.getAssessmentTools().forEach(at -> this.assessmentTools.put(at.getDescription(), at.getValue()));
        this.RAEs = new HashMap<>();
        if(course.getRaeList().isEmpty())
            course.getRaeList().forEach(rae -> RAEs.put(rae.getId(), rae.getDescription()));
    }

    public CourseDTO() {
        this.cdioList = new HashMap<>();
        this.sections =  new HashMap<>();
        this.assessmentTools = new HashMap<>();
        this.RAEs = new HashMap<>();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Float, String> getCdioList() {
        return cdioList;
    }

    public void setCdioList(Map<Float, String> cdioList) {
        this.cdioList = cdioList;
    }

    public Map<Integer, String> getSections() {
        return sections;
    }

    public void setSections(Map<Integer, String> sections) {
        this.sections = sections;
    }

    public Map<String, Double> getAssessmentTools() {
        return assessmentTools;
    }

    public void setAssessmentTools(Map<String, Double> assessmentTools) {
        this.assessmentTools = assessmentTools;
    }

    public Map<Long, String> getRAEs() {
        return RAEs;
    }

    public void setRAEs(Map<Long, String> RAEs) {
        this.RAEs = RAEs;
    }
}
