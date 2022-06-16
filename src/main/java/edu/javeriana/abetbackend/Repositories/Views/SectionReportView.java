package edu.javeriana.abetbackend.Repositories.Views;

import edu.javeriana.abetbackend.Entities.Views.SectionReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionReportView extends CrudRepository<SectionReport, Long> {
    Optional<SectionReport> findBySectionIdAndSemester(Long sectionId, Integer semester);
}
