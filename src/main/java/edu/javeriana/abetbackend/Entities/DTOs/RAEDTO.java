package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.RAE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RAEDTO {

    private Long RAEId;
    private String description;
    private Long courseId;
    private List<AssessmentToolDTO> assessmentTools;
    private List<Float> cdioList;

    public RAEDTO() {
        this.assessmentTools = new ArrayList<>();
        this.cdioList = new ArrayList<>();
    }

    public RAEDTO(RAE rae) {
        this.RAEId = rae.getRAEId();
        this.description = rae.getDescription();
        this.courseId = rae.getCourse().getCourseId();
        this.assessmentTools = new ArrayList<>();
        rae.getAssessmentTools().forEach(at -> assessmentTools.add(new AssessmentToolDTO(at)));
        this.cdioList = new ArrayList<>();
        rae.getCdioList().forEach(cdio -> cdioList.add(cdio.getNumber()));
    }

    public List<AssessmentToolDTO> getAssessmentTools() {
        return assessmentTools;
    }

    public void setAssessmentTools(List<AssessmentToolDTO> assessmentTools) {
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

    public Long  getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
