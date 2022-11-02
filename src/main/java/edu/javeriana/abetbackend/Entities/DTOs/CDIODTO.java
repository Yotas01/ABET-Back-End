package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.CDIO;

import java.util.ArrayList;
import java.util.List;

public class CDIODTO {

    private Float number;
    private String description;
    private List<Integer> outcomes;

    public CDIODTO(CDIO cdio) {
        this.description = cdio.getDescription();
        this.number = cdio.getNumber();
        this.outcomes = new ArrayList<>();
        if (!cdio.getOutcomes().isEmpty())
            cdio.getOutcomes().forEach(outcome -> this.outcomes.add(outcome.getOutcomeId()));
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

    public void addOutcome(Integer outcome){
        this.outcomes.add(outcome);
    }

    public void removeOutcome(Integer outcome){
        this.outcomes.remove(outcome);
    }
}
