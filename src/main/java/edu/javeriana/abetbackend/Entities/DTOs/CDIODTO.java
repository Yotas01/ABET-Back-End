package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.CDIO;

import java.util.ArrayList;
import java.util.List;

public class CDIODTO {
    private String description;
    private Float number;
    private List<Integer> outcomes;
    private List<String> RAEs;
    private List<String> courses;

    public CDIODTO(CDIO cdio) {
        this.description = cdio.getDescription();
        this.number = cdio.getNumber();
        this.outcomes = new ArrayList<>();
        this.RAEs = new ArrayList<>();
        this.courses = new ArrayList<>();
        cdio.getOutcomes().forEach(outcome -> this.outcomes.add(outcome.getOutcomeId()));
        cdio.getCourses().forEach(course -> this.courses.add(course.getName()));
        cdio.getRAEs().forEach(rae -> this.RAEs.add(rae.toString()));
    }

    public CDIODTO() {
    }

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Integer> outcomes) {
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
