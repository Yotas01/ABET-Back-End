package edu.javeriana.abetbackend.Repositories.Views;

import edu.javeriana.abetbackend.Entities.Views.CourseReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.Optional;

@Repository
public interface CourseReportView extends CrudRepository<CourseReport, Long> {
    Optional<CourseReport> findByCourseIdAndSemester(Long courseId, Integer semester);
}
