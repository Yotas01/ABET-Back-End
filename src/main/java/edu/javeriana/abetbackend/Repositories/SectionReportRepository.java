package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Entities.SectionReport;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SectionReportRepository extends CrudRepository<SectionReport, Long> {
    Optional<SectionReport> findBySection(Section section);
}
