package edu.javeriana.abetbackend.Repositories.Views;

import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Ids.Course_has_CdioId;
import edu.javeriana.abetbackend.Entities.Views.CDIOSummaryForCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CDIOSummaryForCourseView extends CrudRepository<CDIOSummaryForCourse, Course_has_CdioId> {
    Optional<List<CDIOSummaryForCourse>> findAllByCdio(CDIO cdio);
    Optional<List<CDIOSummaryForCourse>> findAllByCourse(Course course);
}
