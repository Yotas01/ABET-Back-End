package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.SemesterReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemesterReportRepository extends CrudRepository<SemesterReport, Long> {
    Optional<SemesterReport> findBySemester(Integer semester);
}
