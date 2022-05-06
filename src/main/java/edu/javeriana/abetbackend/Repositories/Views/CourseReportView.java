package edu.javeriana.abetbackend.Repositories.Views;

import edu.javeriana.abetbackend.Entities.Views.CourseReport;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseReportView extends CrudRepository<CourseReport, Long> {
    Optional<CourseReport> findByCourseIdAndSemester(Long courseId, Integer semester);
}
