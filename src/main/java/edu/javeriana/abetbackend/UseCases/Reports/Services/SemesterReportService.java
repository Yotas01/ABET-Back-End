package edu.javeriana.abetbackend.UseCases.Reports.Services;

import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Entities.SemesterReport;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.SemesterReportRepository;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.OutcomeFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SemesterReportService {

    @Autowired
    private SemesterReportRepository repository;
    @Autowired
    private OutcomeFinder outcomeFinder;

    public void saveSemesterReport(SemesterReport report, Integer outcomeId){
        Outcome outcome = outcomeFinder.findOutcomeById(outcomeId);
        Optional<SemesterReport> optionalSemesterReport = repository.findBySemesterAndOutcome(report.getSemester(), outcome);
        if(optionalSemesterReport.isPresent()){
            SemesterReport reportToUpdate = optionalSemesterReport.get();
            reportToUpdate.setImprovementActions(report.getImprovementActions());
            reportToUpdate.setPerformance(report.getPerformance());
            repository.save(reportToUpdate);
        }
        else
            repository.save(report);
    }

    public SemesterReport getSemesterReport(Integer semester, Integer outcomeId){
        Outcome outcome = outcomeFinder.findOutcomeById(outcomeId);
        Optional<SemesterReport> optionalSemesterReport = repository.findBySemesterAndOutcome(semester, outcome);
        if (optionalSemesterReport.isEmpty())
            return new SemesterReport(semester, outcome, "", "");
        else
            return optionalSemesterReport.get();
    }

}
