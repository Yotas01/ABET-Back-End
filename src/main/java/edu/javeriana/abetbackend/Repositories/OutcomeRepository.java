package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.Outcome;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
public interface OutcomeRepository extends CrudRepository<Outcome,Integer> {

}
