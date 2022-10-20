package edu.javeriana.abetbackend.UseCases.Reports.Services;

import edu.javeriana.abetbackend.Entities.SemesterReport;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.SemesterReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SemesterReportService {

    @Autowired
    private SemesterReportRepository repository;

    public void saveSemesterReport(SemesterReport report){
        Optional<SemesterReport> optionalSemesterReport = repository.findBySemester(report.getSemester());
        if(optionalSemesterReport.isPresent()){
            SemesterReport reportToUpdate = optionalSemesterReport.get();
            reportToUpdate.setImprovementActions(report.getImprovementActions());
            reportToUpdate.setPerformance(report.getPerformance());
            repository.save(reportToUpdate);
        }
        else
            repository.save(report);
    }

    public SemesterReport getSemesterReport(Integer semester){
        Optional<SemesterReport> optionalSemesterReport = repository.findBySemester(semester);
        if (optionalSemesterReport.isEmpty())
            return new SemesterReport(null, semester, "", "");
        else
            return optionalSemesterReport.get();
    }

}
