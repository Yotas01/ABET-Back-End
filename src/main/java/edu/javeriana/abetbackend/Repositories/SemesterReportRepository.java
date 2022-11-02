package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.Outcome;
import edu.javeriana.abetbackend.Entities.SemesterReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemesterReportRepository extends CrudRepository<SemesterReport, Integer> {
    Optional<SemesterReport> findBySemesterAndOutcome(Integer semester, Outcome outcome);
}
