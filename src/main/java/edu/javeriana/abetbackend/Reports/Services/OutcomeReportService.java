package edu.javeriana.abetbackend.Reports.Services;

import edu.javeriana.abetbackend.CRUD.Services.Find.OutcomeFinder;
import edu.javeriana.abetbackend.Entities.Views.OutcomeReport;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.Views.OutcomeReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OutcomeReportService {

    @Autowired
    private OutcomeReportView reportView;
    @Autowired
    private OutcomeFinder outcomeFinder;

    public OutcomeReport getOutcomeReportByIdAndSemester(Integer outcomeId, Integer semester){
        outcomeFinder.findOutcomeById(outcomeId);
        Optional<OutcomeReport> report = reportView.findByOutcomeIdAndSemester(outcomeId, semester);
        if(report.isEmpty())
            throw new NotFound("The report for the outcome " + outcomeId + " and semester "
                    + semester + " was not found");
        return report.get();
    }
}
