package edu.javeriana.abetbackend.Repositories.Views;

import edu.javeriana.abetbackend.Entities.Views.OutcomeSummary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeSummaryView extends CrudRepository<OutcomeSummary, Integer> {
}
