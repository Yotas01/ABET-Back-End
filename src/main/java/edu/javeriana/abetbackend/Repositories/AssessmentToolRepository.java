package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.RAE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssessmentToolRepository extends CrudRepository<AssessmentTool, Long> {
    Optional<List<AssessmentTool>> findAllByRae(RAE rae);
}
