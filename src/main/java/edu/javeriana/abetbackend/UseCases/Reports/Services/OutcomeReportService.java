package edu.javeriana.abetbackend.UseCases.Reports.Services;

import edu.javeriana.abetbackend.Entities.DTOs.OutcomeReportDTO;
import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Entities.Views.CDIOSummary;
import edu.javeriana.abetbackend.Entities.Views.OutcomeSummary;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.Views.CDIOSummaryView;
import edu.javeriana.abetbackend.Repositories.Views.OutcomeSummaryView;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.OutcomeFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OutcomeReportService {

    @Autowired
    private OutcomeFinder outcomeFinder;
    @Autowired
    private OutcomeSummaryView outcomeSummaryView;
    @Autowired
    private CDIOSummaryView cdioSummaryView;

    public OutcomeReportDTO getOutcomeSummary(Integer outcomeId){
        OutcomeReportDTO dto = new OutcomeReportDTO();
        Outcome outcome = outcomeFinder.findOutcomeById(outcomeId);
        Optional<OutcomeSummary> outcomeSummary = outcomeSummaryView.findById(outcomeId);
        if(outcomeSummary.isEmpty())
            throw new NotFound("The summary for the outcome " + outcomeId + " was not found");
        dto.setOutcomeSummary(outcomeSummary.get());
        outcome.getCdioList().forEach(cdio -> {
            Optional<CDIOSummary> cdioSummary = cdioSummaryView.findById(cdio.getNumber());
            cdioSummary.ifPresent(dto::addCDIOSummary);
        });
        return dto;
    }
}
