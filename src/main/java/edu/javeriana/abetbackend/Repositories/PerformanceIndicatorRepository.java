package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.PerformanceIndicator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

@Repository
public interface PerformanceIndicatorRepository extends CrudRepository<PerformanceIndicator, Long> {
    Optional<List<PerformanceIndicator>> findByAssessmentTool(AssessmentTool assessmentTool);
    void deleteAllByAssessmentTool(AssessmentTool assessmentTool);
}
