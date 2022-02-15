package edu.javeriana.abetbackend.Entities.ResponseEntities;

import edu.javeriana.abetbackend.Entities.Outcome;

import java.util.ArrayList;
import java.util.List;

public class ResponseOutcome {
    private Long id;
    private String description;
    private List<Float> cdios =  new ArrayList<>();

    public ResponseOutcome(Outcome outcome) {
        this.id = outcome.getOutcomeId();
        this.description = outcome.getDescription();
        outcome.getCDIos().forEach(cdio -> this.getCdios().add(cdio.getNumber()));
    }

    public ResponseOutcome() {
    }

    public Long getId() {
            return id;
    }

    public void setId(Long id) {
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
