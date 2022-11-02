package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.Outcome;

import java.util.ArrayList;
import java.util.List;

public class OutcomeDTO {
    private Integer id;
    private String description;
    private List<Float> cdioList =  new ArrayList<>();

    public OutcomeDTO(Outcome outcome) {
        this.id = outcome.getOutcomeId();
        this.description = outcome.getDescription();
        this.cdioList = new ArrayList<>();
        if(outcome.getCdioList() != null && !outcome.getCdioList().isEmpty())
            outcome.getCdioList().forEach(cdio -> this.cdioList.add(cdio.getNumber()));
    }

    public OutcomeDTO() {
    }

    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
            this.id = id;
    }

    public String getDescription() {
            return description;
    }

    public void setDescription(String description) {
            this.description = description;
    }

    public List<Float> getCdioList() {
        return cdioList;
    }

    public void setCdioList(List<Float> cdioList) {
        this.cdioList = cdioList;
    }
}
