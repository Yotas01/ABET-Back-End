package edu.javeriana.abetbackend.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.CRUD.Services.Find.OutcomeFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.DTOs.OutcomeDTO;
import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Exceptions.AlreadyExists;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutcomeCRUD {

    @Autowired
    private OutcomeRepository repository;
    @Autowired
    private OutcomeFinder finder;
    @Autowired
    private CDIOFinder cdioFinder;

    public void saveOutcome(OutcomeDTO outcomeDTO) {
        try{
            Outcome existingOutcome = finder.findOutcomeById(outcomeDTO.getId());
        }catch (NotFound exception){
            Outcome createdOutcome = new Outcome(outcomeDTO.getId(), outcomeDTO.getDescription());
            for (Float cdioNumber: outcomeDTO.getCdios()) {
                CDIO cdio = cdioFinder.findCDIOById(cdioNumber);
                createdOutcome.addCDIo(cdio);
                cdio.addOutcome(createdOutcome);
                repository.save(createdOutcome);
            }
            return;
        }
        throw new AlreadyExists("The outcome with the id " + outcomeDTO.getId() + " already exists");
    }

    public Outcome updateOutcome(OutcomeDTO outcomeDTO, Integer outcomeId){
        Outcome updatedOutcome = finder.findOutcomeById(outcomeId);
        updatedOutcome.setDescription(outcomeDTO.getDescription());
        updatedOutcome.getCDIos().clear();
        for (Float cdioNumber: outcomeDTO.getCdios()) {
            updatedOutcome.addCDIo(cdioFinder.findCDIOById(cdioNumber));
        }
        repository.save(updatedOutcome);
        return updatedOutcome;
    }

    public void deleteOutcome(Outcome outcome){
        Outcome outcomeToDelete = finder.findOutcomeById(outcome.getOutcomeId());
        repository.delete(outcomeToDelete);
    }
}