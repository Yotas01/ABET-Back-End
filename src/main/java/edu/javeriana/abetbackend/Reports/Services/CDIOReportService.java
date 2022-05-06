package edu.javeriana.abetbackend.Reports.Services;

import edu.javeriana.abetbackend.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.Views.CDIOReport;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.Views.CDIOReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CDIOReportService {

    @Autowired
    private CDIOReportView reportView;
    @Autowired
    private CDIOFinder cdioFinder;

    public CDIOReport getCDIOReportByNumberAndSemester(Float cdioNumber, Integer semester){
        cdioFinder.findCDIOById(cdioNumber);
        Optional<CDIOReport> report = reportView.findByCdioNumberAndSemester(cdioNumber, semester);
        if(report.isEmpty())
            throw new NotFound("The report for the CDIO " + cdioNumber + " and semester " + semester + " was not found");
        return report.get();
    }
}
