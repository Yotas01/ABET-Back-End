package edu.javeriana.abetbackend.Reports.Services;

import edu.javeriana.abetbackend.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Views.CourseReport;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.Views.CourseReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseReportService {

    @Autowired
    private CourseReportView reportView;
    @Autowired
    private CourseFinder courseFinder;

    public CourseReport getCourseReportByIdAndSemester(Integer courseNumber, Integer semester){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Optional<CourseReport> report = reportView.findByCourseIdAndSemester(course.getCourseId(),semester);
        if(report.isEmpty())
            throw new NotFound("The report for the course " + courseNumber + " and semester "
                    + semester + " was not found");
        return report.get();
    }
}
