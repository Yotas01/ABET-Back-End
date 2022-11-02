package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.PerformanceIndicator;
import edu.javeriana.abetbackend.Entities.RAE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerformanceIndicatorRepository extends CrudRepository<PerformanceIndicator, Long> {
    Optional<List<PerformanceIndicator>> findByRae(RAE rae);
}
