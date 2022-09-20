package edu.javeriana.abetbackend.UseCases.Reports.Services;

import edu.javeriana.abetbackend.Entities.Views.OutcomeSummary;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.Views.OutcomeSummaryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OutcomeReportService {

    @Autowired
    private OutcomeSummaryView outcomeSummaryView;

    public OutcomeSummary getOutcomeSummary(Integer outcomeId){
        Optional<OutcomeSummary> outcomeSummary = outcomeSummaryView.findById(outcomeId);
        if(outcomeSummary.isEmpty())
            throw new NotFound("The summary for the outcome " + outcomeId + " was not found");
        return outcomeSummary.get();
    }
}
