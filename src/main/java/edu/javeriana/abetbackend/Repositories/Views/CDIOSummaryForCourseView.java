package edu.javeriana.abetbackend.Repositories.Views;

import edu.javeriana.abetbackend.Entities.Views.CDIOSummaryForCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDIOSummaryForCourseView extends CrudRepository<CDIOSummaryForCourse, Long> {
}
