package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.RAE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RAEDTO {

    private Long raeId;
    private Integer courseId;
    private String description;
    private Integer semester;
    private Map<Float, String> cdioList;
    private List<PerformanceIndicatorDTO> performanceIndicators;

    public RAEDTO() {
        this.performanceIndicators = new ArrayList<>();
        this.cdioList = new HashMap<>();
    }

    public RAEDTO(RAE rae) {
        this.raeId = rae.getId();
        this.courseId = rae.getCourse().getCourseId();
        this.description = rae.getDescription();
        this.semester = rae.getSemester();
        this.cdioList = new HashMap<>();
        rae.getCdioList().forEach(cdio -> this.cdioList.put(cdio.getNumber(), cdio.getDescription()));
        this.performanceIndicators = new ArrayList<>(rae.getPerformanceIndicators().stream().map(PerformanceIndicatorDTO::new).toList());
    }

    public Long getRaeId() {
        return raeId;
    }

    public void setRaeId(Long raeId) {
        this.raeId = raeId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Map<Float, String> getCdioList() {
        return cdioList;
    }

    public void setCdioList(Map<Float, String> cdioList) {
        this.cdioList = cdioList;
    }

    public List<PerformanceIndicatorDTO> getPerformanceIndicators() {
        return performanceIndicators;
    }

    public void setPerformanceIndicators(List<PerformanceIndicatorDTO> performanceIndicators) {
        this.performanceIndicators = performanceIndicators;
    }
}
