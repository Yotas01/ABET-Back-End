package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Ids.SectionId;
import edu.javeriana.abetbackend.Entities.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends CrudRepository<Section, SectionId> {
    Optional<Section> findByCourseAndClassNumberAndSemester(Course course, Integer classNumber, Integer semester);
    Optional<List<Section>> findAllByProfessorAndSemester(String professor, Integer semester);
    Optional<List<Section>> findAllByCourseAndSemester(Course course, Integer semester);
}
