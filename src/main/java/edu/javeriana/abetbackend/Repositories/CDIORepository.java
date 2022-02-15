package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.CDIO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CDIORepository extends CrudRepository<CDIO,Long> {
    Optional<CDIO> findCDIOByNumber(Float number);
}
