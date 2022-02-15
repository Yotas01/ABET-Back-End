package edu.javeriana.abetbackend.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.CRUD.Services.Find.OutcomeFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Exceptions.OutcomeNotFoundById;
import edu.javeriana.abetbackend.Repositories.OutcomeRepository;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OutcomeCRUD {

    @Autowired
    private OutcomeRepository repository;
    @Autowired
    private OutcomeFinder finder;

    public void saveOutcome(Outcome outcome) {
        repository.save(outcome);
    }

    public Outcome updateOutcome(Outcome outcome){
        Outcome updatedOutcome = finder.findOutcomeById(outcome.getOutcomeId());
        updatedOutcome.setDescription(outcome.getDescription());
        updatedOutcome.setCDIos(new ArrayList<>(outcome.getCDIos()));
        repository.save(updatedOutcome);
        return updatedOutcome;
    }

    public void deleteOutcome(Outcome outcome){
        Outcome outcomeToDelete = finder.findOutcomeById(outcome.getOutcomeId());
        repository.delete(outcomeToDelete);
    }
}