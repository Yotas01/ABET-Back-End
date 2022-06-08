package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends CrudRepository<Section, Long> {
    Optional<List<Section>> findAllByProfessor(String professor);
    Optional<Section> findSectionByNumberAndCourse(Integer number, Course course);
    Optional<Section> findSectionByNumberAndCourseAndSemester(Integer number, Course course, Integer semester);
    Optional<List<Section>> findSectionByCourseAndSemester(Course course, Integer semester);
}
