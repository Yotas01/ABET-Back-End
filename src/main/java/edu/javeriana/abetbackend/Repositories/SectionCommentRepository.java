package edu.javeriana.abetbackend.Repositories;

import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Entities.SectionComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionCommentRepository extends CrudRepository<SectionComment, Long> {
    Optional<SectionComment> findBySection(Section section);
}
