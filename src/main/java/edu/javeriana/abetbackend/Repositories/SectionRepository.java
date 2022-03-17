package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Section;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends CrudRepository<Section, Long> {
    Optional<List<Section>> findAllByProfessor(String professor);
    Optional<Section> findSectionByNumberAndCourse(Integer number, Course course);
}
