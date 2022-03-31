package edu.javeriana.abetbackend.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.CRUD.Services.Find.OutcomeFinder;
import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Exceptions.AlreadyExists;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OutcomeCRUD {

    @Autowired
    private OutcomeRepository repository;
    @Autowired
    private OutcomeFinder finder;

    public void saveOutcome(Outcome outcome) {
        try{
            Outcome existingOutcome = finder.findOutcomeById(outcome.getOutcomeId());
        }catch (NotFound exception){
            repository.save(outcome);
            return;
        }
        throw new AlreadyExists("The outcome with the id " + outcome.getOutcomeId() + " already exists");
    }

    public Outcome updateOutcome(Outcome outcome, Integer outcomeId){
        Outcome updatedOutcome = finder.findOutcomeById(outcomeId);
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