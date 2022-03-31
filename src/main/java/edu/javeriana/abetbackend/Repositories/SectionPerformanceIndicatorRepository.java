package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Entities.SectionAssessmentTool;
import edu.javeriana.abetbackend.Entities.SectionPerformanceIndicator;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SectionPerformanceIndicatorRepository extends CrudRepository<SectionPerformanceIndicator, Long> {
    Optional<List<SectionPerformanceIndicator>> findAllBySectionAssessmentTool(SectionAssessmentTool sectionAssessmentTool);
    void deleteAllBySectionAssessmentTool(SectionAssessmentTool sectionAssessmentTool);
}
