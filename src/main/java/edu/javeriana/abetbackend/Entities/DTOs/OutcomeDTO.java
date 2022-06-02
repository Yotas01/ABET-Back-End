package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.Outcome;

import java.util.ArrayList;
import java.util.List;

public class OutcomeDTO {
    private Integer id;
    private String description;
    private List<Float> cdios =  new ArrayList<>();

    public OutcomeDTO(Outcome outcome) {
        this.id = outcome.getOutcomeId();
        this.description = outcome.getDescription();
        if (!outcome.getCDIos().isEmpty())
            outcome.getCDIos().forEach(cdio -> this.getCdios().add(cdio.getNumber()));
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

    public List<Float> getCdios() {
            return cdios;
    }

    public void setCdios(List<Float> cdios) {
            this.cdios = cdios;
    }
}
