package edu.javeriana.abetbackend.UseCases.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Entities.SectionComment;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.SectionCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SectionCommentFinder {

    @Autowired
    private SectionCommentRepository repository;
    @Autowired
    private SectionFinder sectionFinder;

    public SectionComment getSectionReviewComment(Integer courseNumber, Integer sectionNumber, Integer semester){
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber, sectionNumber,semester);
        Optional<SectionComment> comment = repository.findBySection(section);
        if(comment.isEmpty())
            throw new NotFound("A section review comment for the course: " + courseNumber + " section: " + sectionNumber
            + " and semester: " + semester + " was not found");
        return comment.get();
    }
}
