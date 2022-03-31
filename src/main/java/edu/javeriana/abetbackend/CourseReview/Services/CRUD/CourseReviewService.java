package edu.javeriana.abetbackend.CourseReview.Services.CRUD;

import edu.javeriana.abetbackend.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.CRUD.Services.Find.SectionFinder;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.CourseReview;
import edu.javeriana.abetbackend.Entities.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseReviewService {

    @Autowired
    private CourseFinder courseFinder;
    @Autowired
    private SectionFinder sectionFinder;

    public CourseReview getCourseForReview(Integer courseNumber, Integer sectionNumber, Integer semester){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber,sectionNumber, semester);
        return new CourseReview(course,section);
    }
}
