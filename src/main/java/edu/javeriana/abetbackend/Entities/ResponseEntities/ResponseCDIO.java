package edu.javeriana.abetbackend.Entities.ResponseEntities;

import edu.javeriana.abetbackend.Entities.CDIO;

import java.util.List;

public class ResponseCDIO {
    private Long CDIOId;
    private String description;
    private Float number;
    private List<Long> outcomes;
    private List<String> RAEs;
    private List<String> courses;

    public ResponseCDIO(CDIO cdio) {
        this.CDIOId = cdio.getCDIOId();
        this.description = cdio.getDescription();
        this.number = cdio.getNumber();
        cdio.getOutcomes().forEach(outcome -> this.outcomes.add(outcome.getOutcomeId()));
        cdio.getCourses().forEach(course -> this.courses.add(course.getName()));
        cdio.getRAEs().forEach(rae -> this.RAEs.add(rae.getDescription()));
    }

    public ResponseCDIO() {
    }

    public Long getCDIOId() {
        return CDIOId;
    }

    public void setCDIOId(Long CDIOId) {
        this.CDIOId = CDIOId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }

    public List<Long> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Long> outcomes) {
        this.outcomes = outcomes;
    }

    public List<String> getRAEs() {
        return RAEs;
    }

    public void setRAEs(List<String> RAEs) {
        this.RAEs = RAEs;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
