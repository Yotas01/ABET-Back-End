package edu.javeriana.abetbackend.UseCases.Reports.Services;

import edu.javeriana.abetbackend.Entities.Views.CDIOSummary;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.Views.CDIOSummaryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CDIOReportService {

    @Autowired
    private CDIOSummaryView cdioSummaryView;

    public CDIOSummary getCDIOReport(Float cdioNumber){
        Optional<CDIOSummary> cdioSummary = cdioSummaryView.findById(cdioNumber);
        if(cdioSummary.isEmpty())
            throw new NotFound("The summary for the cdio " + cdioNumber + " was not found");
        return cdioSummary.get();
    }
}
