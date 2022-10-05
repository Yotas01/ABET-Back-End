package edu.javeriana.abetbackend.UseCases.Reports.Services;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.DTOs.CourseReportDTO;
import edu.javeriana.abetbackend.Entities.DTOs.RAESummaryDTO;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Entities.SectionReviewComment;
import edu.javeriana.abetbackend.Entities.Views.CDIOSummaryForCourse;
import edu.javeriana.abetbackend.Entities.Views.RAESummary;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.SectionReviewCommentRepository;
import edu.javeriana.abetbackend.Repositories.Views.CDIOSummaryForCourseView;
import edu.javeriana.abetbackend.Repositories.Views.RAESummaryView;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.RAEFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseReportService {

    @Autowired
    private RAESummaryView raeSummaryView;
    @Autowired
    private CDIOSummaryForCourseView cdioSummaryForCourseView;
    @Autowired
    private CourseFinder courseFinder;
    @Autowired
    private SectionReviewCommentRepository commentRepository;

    //TODO: Add semester to Views
    public CourseReportDTO getCourseReport(Integer courseNumber, Integer semester){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        List<RAE> raes = course.getRAEs();
        List<RAESummaryDTO> raeSummaries = new ArrayList<>();

        raes.forEach(rae -> {
            Optional<RAESummary> summary = raeSummaryView.findById(rae.getRAEId());
            if(summary.isEmpty())
                throw new NotFound("There was no RAE summary found for the rae with id " + rae.getRAEId());
            RAESummaryDTO dto = new RAESummaryDTO(rae.getRAEId(), rae.getDescription(), summary.get().getExemplary(),
                    summary.get().getCompetent(), summary.get().getBelow());
            rae.getAssessmentTools().forEach(assessmentTool -> dto.addAssessmentTool(assessmentTool.getDescription()));
            raeSummaries.add(dto);
        });

        Optional<List<CDIOSummaryForCourse>> cdioSummary = cdioSummaryForCourseView.findAllByCourseId(course.getCourseId());
        if (cdioSummary.isEmpty())
            throw new NotFound("There were no cdio summaries found for the course " + courseNumber);

        CourseReportDTO courseReportDTO = new CourseReportDTO(raeSummaries, cdioSummary.get());

        course.getSections().forEach(section -> {
            Optional<SectionReviewComment> comment = commentRepository.findBySectionAndSemester(section, semester);
            comment.ifPresent(sectionReviewComment -> courseReportDTO.addComment(sectionReviewComment.getComment()));
        });

        return courseReportDTO;
    }
}
