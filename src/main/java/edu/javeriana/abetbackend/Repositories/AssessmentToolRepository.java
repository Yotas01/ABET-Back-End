package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.RAE;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AssessmentToolRepository extends CrudRepository<AssessmentTool, Long> {
    Optional<List<AssessmentTool>> findAllByRae(RAE rae);
}
