package edu.javeriana.abetbackend.Repositories.Views;

import edu.javeriana.abetbackend.Entities.Views.OutcomeReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.Optional;

@Repository
public interface OutcomeReportView extends CrudRepository<OutcomeReport, Integer> {
    Optional<OutcomeReport> findByOutcomeIdAndSemester(Integer outcomeId, Integer semester);
}
