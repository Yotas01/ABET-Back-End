package edu.javeriana.abetbackend.UseCases.Reports.Services;

import edu.javeriana.abetbackend.Entities.DTOs.CourseReportDTO;
import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Entities.Views.SectionReport;
import edu.javeriana.abetbackend.Repositories.Views.SectionReportView;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Views.CourseReport;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.Views.CourseReportView;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.SectionFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseReportService {

    @Autowired
    private CourseReportView courseReportView;
    @Autowired
    private SectionReportView sectionReportView;
    @Autowired
    private CourseFinder courseFinder;

    public CourseReportDTO getCourseReportByIdAndSemester(Integer courseNumber, Integer semester){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        List<SectionReport> sectionsReport = new ArrayList<>();
        for (Section section : course.getSections()) {
            Optional<SectionReport> sectionReport = sectionReportView.findBySectionIdAndSemester(section.getSectionId(), semester);
            if(sectionReport.isEmpty())
                throw new NotFound("The Section Report for the section id " + section.getSectionId() +
                        " from the course " + courseNumber + " and the semester " + semester + " was not found");
            sectionsReport.add(sectionReport.get());
        }
        Optional<CourseReport> courseReport = courseReportView.findByCourseIdAndSemester(course.getCourseId(),semester);
        if(courseReport.isEmpty())
            throw new NotFound("The report for the course " + courseNumber + " and semester "
                    + semester + " was not found");
        return new CourseReportDTO(courseReport.get(),sectionsReport);
    }
}
