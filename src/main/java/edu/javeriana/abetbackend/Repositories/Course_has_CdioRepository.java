package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.Course_has_CDIO;
import edu.javeriana.abetbackend.Entities.Ids.Course_has_CdioId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
public interface Course_has_CdioRepository extends CrudRepository<Course_has_CDIO, Course_has_CdioId> {

}
