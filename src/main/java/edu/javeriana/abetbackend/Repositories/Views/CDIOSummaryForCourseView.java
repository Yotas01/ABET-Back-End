package edu.javeriana.abetbackend.Repositories.Views;

import edu.javeriana.abetbackend.Entities.Views.CDIOSummaryForCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CDIOSummaryForCourseView extends CrudRepository<CDIOSummaryForCourse, Long> {
    Optional<List<CDIOSummaryForCourse>> findAllByCourseId(Long courseId);
    @Query(value="SELECT * FROM CDIO_SUMMARY_FOR_COURSE where cdio_number= ?1", nativeQuery = true)
    Optional<List<CDIOSummaryForCourse>> findAllByCdioNumber(Float cdioNumber);
}
