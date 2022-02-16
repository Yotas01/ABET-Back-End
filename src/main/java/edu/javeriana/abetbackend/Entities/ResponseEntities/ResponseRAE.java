package edu.javeriana.abetbackend.Entities.ResponseEntities;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.RAE;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseRAE {

    private Long RAEId;
    private String description;
    private Map<Integer, String> course;
    private List<String> assessmentTools;
    private List<Float> cdioList;

    public ResponseRAE() {
        this.course = new HashMap<>();
        this.assessmentTools = new ArrayList<>();
        this.cdioList = new ArrayList<>();
    }

    public ResponseRAE(RAE rae) {
        this.RAEId = rae.getRAEId();
        this.description = rae.getDescription();
        this.course = new HashMap<>();
        course.put(rae.getCourse().getNumber(),rae.getCourse().getName());
        this.assessmentTools = new ArrayList<>();
        rae.getAssessmentTools().forEach(at -> assessmentTools.add(at.getDescription()));
        this.cdioList = new ArrayList<>();
        rae.getCdioList().forEach(cdio -> cdioList.add(cdio.getNumber()));
    }

    public List<String> getAssessmentTools() {
        return assessmentTools;
    }

    public void setAssessmentTools(List<String> assessmentTools) {
        this.assessmentTools = assessmentTools;
    }

    public List<Float> getCdioList() {
        return cdioList;
    }

    public void setCdioList(List<Float> cdioList) {
        this.cdioList = cdioList;
    }

    public Long getRAEId() {
        return RAEId;
    }

    public void setRAEId(Long RAEId) {
        this.RAEId = RAEId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Integer, String>  getCourse() {
        return course;
    }

    public void setCourse(Map<Integer, String>  course) {
        this.course = course;
    }
}
