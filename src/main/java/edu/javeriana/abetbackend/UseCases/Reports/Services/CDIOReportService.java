package edu.javeriana.abetbackend.UseCases.Reports.Services;

import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.DTOs.CDIOReportDTO;
import edu.javeriana.abetbackend.Entities.Views.CDIOSummary;
import edu.javeriana.abetbackend.Entities.Views.CDIOSummaryForCourse;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.Views.CDIOSummaryForCourseView;
import edu.javeriana.abetbackend.Repositories.Views.CDIOSummaryView;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CDIOFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CDIOReportService {

    @Autowired
    private CDIOSummaryView cdioSummaryView;
    @Autowired
    private CDIOSummaryForCourseView cdioSummaryForCourseView;
    @Autowired
    private CDIOFinder cdioFinder;

    public CDIOReportDTO getCDIOReport(Float cdioNumber){
        CDIO cdio = cdioFinder.findCDIOById(cdioNumber);
        Optional<CDIOSummary> cdioSummary = cdioSummaryView.findById(cdioNumber);
        if(cdioSummary.isEmpty())
            throw new NotFound("The summary for the cdio " + cdioNumber + " was not found");
        Optional<List<CDIOSummaryForCourse>> courseSummaries = cdioSummaryForCourseView.findAllByCdio(cdio);
        if(courseSummaries.isEmpty())
            throw new NotFound("The summary for the cdio " + cdioNumber + " was not found");
        CDIOReportDTO report = new CDIOReportDTO();
        report.setCdioSummary(cdioSummary.get());
        courseSummaries.get().forEach(report::addCourseSummary);
        return report;
    }
}
