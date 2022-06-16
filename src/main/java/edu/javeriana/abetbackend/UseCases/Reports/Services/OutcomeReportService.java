package edu.javeriana.abetbackend.UseCases.Reports.Services;

import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.DTOs.OutcomeReportDTO;
import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Entities.Views.CDIOReport;
import edu.javeriana.abetbackend.Repositories.Views.CDIOReportView;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.OutcomeFinder;
import edu.javeriana.abetbackend.Entities.Views.OutcomeReport;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.Views.OutcomeReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OutcomeReportService {

    @Autowired
    private OutcomeReportView outcomeReportView;
    @Autowired
    private CDIOReportView  cdioReportView;
    @Autowired
    private OutcomeFinder outcomeFinder;

    public OutcomeReportDTO getOutcomeReportByIdAndSemester(Integer outcomeId, Integer semester){
        Outcome outcome = outcomeFinder.findOutcomeById(outcomeId);
        List<CDIOReport> cdioReports = new ArrayList<>();
        for (CDIO cdio :outcome.getCDIos()) {
            Optional<CDIOReport> cdioReport = cdioReportView.findByCdioNumberAndSemester(cdio.getNumber(), semester);
            cdioReport.ifPresent(cdioReports::add);
        }
        Optional<OutcomeReport> report = outcomeReportView.findByOutcomeIdAndSemester(outcomeId, semester);
        if(report.isEmpty())
            throw new NotFound("The report for the outcome " + outcomeId + " and semester "
                    + semester + " was not found");
        return new OutcomeReportDTO(report.get(), cdioReports);
    }
}
