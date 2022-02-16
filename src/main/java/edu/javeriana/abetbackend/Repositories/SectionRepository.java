package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.Section;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends CrudRepository<Section, Long> {
    Optional<List<Section>> findSectionByProfessor(String professor);
    Optional<Section> findSectionByNumber(Integer number);
}
