package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Entities.SectionReviewComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionReviewCommentRepository extends CrudRepository<SectionReviewComment, Long> {
    Optional<SectionReviewComment> findBySectionAndSemester(Section section, Integer semester);
}
