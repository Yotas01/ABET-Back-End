package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.PerformanceIndicator;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PerformanceIndicatorRepository extends CrudRepository<PerformanceIndicator, Long> {
    Optional<List<PerformanceIndicator>> findByAssessmentTool(AssessmentTool assessmentTool);
    void deleteAllByAssessmentTool(AssessmentTool assessmentTool);
}
