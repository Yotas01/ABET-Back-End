package edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.Entities.DTOs.CDIODTO;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.OutcomeFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.DTOs.OutcomeDTO;
import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Exceptions.AlreadyExists;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OutcomeCRUD {

    @Autowired
    private OutcomeRepository repository;
    @Autowired
    private OutcomeFinder finder;
    @Autowired
    private CDIOFinder cdioFinder;

    public Outcome createOutcome(OutcomeDTO outcomeDTO) {
        try{
            Outcome existingOutcome = finder.findOutcomeById(outcomeDTO.getId());
        }catch (NotFound exception){
            Outcome createdOutcome = new Outcome(outcomeDTO.getId(), outcomeDTO.getDescription());
            outcomeDTO.getCdioList().forEach(cdiodto -> {
                CDIO cdio = cdioFinder.findCDIOById(cdiodto);
                createdOutcome.addCdio(cdio);
                cdio.addOutcome(createdOutcome);
            });
            repository.save(createdOutcome);
            return createdOutcome;
        }
        throw new AlreadyExists("The outcome with the id " + outcomeDTO.getId() + " already exists");
    }

    public Outcome updateOutcome(OutcomeDTO outcomeDTO, Integer outcomeId){
        Outcome updatedOutcome = finder.findOutcomeById(outcomeId);
        updatedOutcome.setDescription(outcomeDTO.getDescription());
        updatedOutcome.getCdioList().clear();
        for (Float cdio: outcomeDTO.getCdioList()) {
            updatedOutcome.addCdio(cdioFinder.findCDIOById(cdio));
        }
        repository.save(updatedOutcome);
        return updatedOutcome;
    }

    public Outcome deleteOutcome(Integer outcomeId){
        Outcome outcomeToDelete = finder.findOutcomeById(outcomeId);
        repository.delete(outcomeToDelete);
        return outcomeToDelete;
    }
}