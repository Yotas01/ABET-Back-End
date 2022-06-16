package edu.javeriana.abetbackend.UseCases.Reports.Services;

import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.DTOs.CDIOReportDTO;
import edu.javeriana.abetbackend.Entities.Views.CourseReport;
import edu.javeriana.abetbackend.Repositories.Views.CourseReportView;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.Entities.Views.CDIOReport;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.Views.CDIOReportView;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CourseFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CDIOReportService {

    @Autowired
    private CDIOReportView cdioReportView;
    @Autowired
    private CourseReportView courseReportView;
    @Autowired
    private CDIOFinder cdioFinder;

    public CDIOReportDTO getCDIOReportByNumberAndSemester(Float cdioNumber, Integer semester){
        CDIO cdio = cdioFinder.findCDIOById(cdioNumber);
        List<CourseReport> courseReports = new ArrayList<>();
        for (Course course:cdio.getCourses()) {
            Optional<CourseReport> courseReport = courseReportView.findByCourseIdAndSemester(course.getCourseId(), semester);
            if (courseReport.isEmpty())
                throw new NotFound("The report for the course " + course.getNumber() + " from the CDIO " + cdioNumber
                + " and the semester " + semester + " was not found");
            courseReports.add(courseReport.get());
        }
        Optional<CDIOReport> cdioReport = cdioReportView.findByCdioNumberAndSemester(cdioNumber, semester);
        if(cdioReport.isEmpty())
            throw new NotFound("The report for the CDIO " + cdioNumber + " and semester " + semester + " was not found");
        return new CDIOReportDTO(cdioReport.get(), courseReports);
    }
}
