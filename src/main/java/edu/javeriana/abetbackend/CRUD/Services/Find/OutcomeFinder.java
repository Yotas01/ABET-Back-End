package edu.javeriana.abetbackend.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Exceptions.OutcomeNotFoundById;
import edu.javeriana.abetbackend.Repositories.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutcomeFinder {

    @Autowired
    private OutcomeRepository repository;

    public Outcome findOutcomeById(Long outcomeId){
        Optional<Outcome> optionalOutcome = repository.findById(outcomeId);
        if (optionalOutcome.isEmpty())
            throw new OutcomeNotFoundById("The outcome with Id " + outcomeId + " was not found");
        return optionalOutcome.get();
    }

    public List<Outcome> getAllOutcomes(){
        return (List<Outcome>) repository.findAll();
    }
}
