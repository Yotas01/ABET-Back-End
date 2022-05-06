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

    public CourseReport getCourseReportByIdAndSemester(Long courseId, Integer semester){
        Course course = courseFinder.findCourseById(courseId);
        Optional<CourseReport> report = reportView.findByCourseIdAndSemester(courseId,semester);
        if(report.isEmpty())
            throw new NotFound("The report for the course with Id " + courseId + " and semester "
                    + semester + " was not found");
        return report.get();
    }
}
