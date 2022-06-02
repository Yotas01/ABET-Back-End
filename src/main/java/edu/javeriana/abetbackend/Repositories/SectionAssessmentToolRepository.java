package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Entities.SectionAssessmentTool;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SectionAssessmentToolRepository extends CrudRepository<SectionAssessmentTool, Long> {
    Optional<List<SectionAssessmentTool>> findAllBySection(Section section);
    Optional<SectionAssessmentTool> findByAssessmentTool(AssessmentTool assessmentTool);
    Optional<SectionAssessmentTool> findByAssessmentToolAndSemester(AssessmentTool assessmentTool, Integer semester);
    Optional<SectionAssessmentTool> findByAssessmentToolAndSemesterAndSection
            (AssessmentTool assessmentTool, Integer semester, Section section);
}
