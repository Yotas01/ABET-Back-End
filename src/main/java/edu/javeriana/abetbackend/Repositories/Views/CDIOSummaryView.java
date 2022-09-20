package edu.javeriana.abetbackend.Repositories.Views;

import edu.javeriana.abetbackend.Entities.Views.CDIOSummary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDIOSummaryView extends CrudRepository<CDIOSummary, Float> {

}
