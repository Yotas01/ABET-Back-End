package edu.javeriana.abetbackend.CRUD.Services.Relations;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.CDIOCRUD;
import edu.javeriana.abetbackend.CRUD.Services.CRUD.OutcomeCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.CRUD.Services.Find.OutcomeFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.Outcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutcomeRelationsService {

    @Autowired
    private OutcomeCRUD outcomeService;
    @Autowired
    private CDIOCRUD cdioService;
    @Autowired
    private OutcomeFinder outcomeFinder;
    @Autowired
    private CDIOFinder cdioFinder;

    public Outcome addCDIOToOutcome(Long outcomeId, Float cdioNumber){
        Outcome outcome = outcomeFinder.findOutcomeById(outcomeId);
        CDIO cdio = cdioFinder.findCDIOByNumber(cdioNumber);
        outcome.addCDIo(cdio);
        cdio.addOutcome(outcome);
        outcomeService.updateOutcome(outcome);
        cdioService.updateCDIO(cdio);
        return outcome;
    }

    public Outcome deleteCDIOFromOutcome(Long outcomeId, Float cdioNumber){
        Outcome outcome = outcomeFinder.findOutcomeById(outcomeId);
        CDIO cdio = cdioFinder.findCDIOByNumber(cdioNumber);
        outcome.removeCDIo(cdio);
        cdio.removeOutcome(outcome);
        Outcome deletedOutcome = outcomeService.updateOutcome(outcome);
        cdioService.updateCDIO(cdio);
        return deletedOutcome;
    }

}
