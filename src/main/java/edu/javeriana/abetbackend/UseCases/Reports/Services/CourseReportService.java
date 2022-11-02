package edu.javeriana.abetbackend.UseCases.Reports.Services;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.DTOs.CourseReportDTO;
import edu.javeriana.abetbackend.Entities.DTOs.RAESummaryDTO;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Repositories.SectionCommentRepository;
import edu.javeriana.abetbackend.Repositories.Views.CDIOSummaryForCourseView;
import edu.javeriana.abetbackend.Repositories.Views.RAESummaryView;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CourseFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseReportService {

    @Autowired
    private RAESummaryView raeSummaryView;
    @Autowired
    private CDIOSummaryForCourseView cdioSummaryForCourseView;
    @Autowired
    private CourseFinder courseFinder;
    @Autowired
    private SectionCommentRepository commentRepository;

    public CourseReportDTO getCourseReport(Integer courseNumber, Integer semester){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        List<RAE> raes = course.getRaeList();
        List<RAESummaryDTO> raeSummaries = new ArrayList<>();


        return new CourseReportDTO();
    }
}
