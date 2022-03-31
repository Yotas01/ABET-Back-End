package edu.javeriana.abetbackend.CRUD.Services.Relations;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.CDIOCRUD;
import edu.javeriana.abetbackend.CRUD.Services.CRUD.OutcomeCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.CRUD.Services.Find.OutcomeFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Exceptions.AlreadyContains;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Outcome_CDIORelationsService {

    @Autowired
    private OutcomeCRUD outcomeService;
    @Autowired
    private CDIOCRUD cdioService;
    @Autowired
    private OutcomeFinder outcomeFinder;
    @Autowired
    private CDIOFinder cdioFinder;

    public void addCDIOToOutcome(Integer outcomeId, Float cdioNumber){
        Outcome outcome = outcomeFinder.findOutcomeById(outcomeId);
        CDIO cdio = cdioFinder.findCDIOById(cdioNumber);
        if(outcome.getCDIos().contains(cdio))
            throw new AlreadyContains("The outcome " + outcomeId + " already contains the cdio " + cdioNumber);
        outcome.addCDIo(cdio);
        cdio.addOutcome(outcome);
        outcomeService.updateOutcome(outcome, outcomeId);
        cdioService.updateCDIO(cdio);
    }

    public void deleteCDIOFromOutcome(Integer outcomeId, Float cdioNumber){
        Outcome outcome = outcomeFinder.findOutcomeById(outcomeId);
        CDIO cdio = cdioFinder.findCDIOById(cdioNumber);
        if(!outcome.getCDIos().contains(cdio))
            throw new DoesNotContain("The outcome " + outcomeId + " does not contain the cdio " + cdioNumber);
        outcome.removeCDIo(cdio);
        cdio.removeOutcome(outcome);
        Outcome deletedOutcome = outcomeService.updateOutcome(outcome, outcomeId);
        cdioService.updateCDIO(cdio);
    }

}
