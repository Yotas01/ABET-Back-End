package edu.javeriana.abetbackend.UseCases.Reviews.Services.Find;

import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.AssessmentToolFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.SectionFinder;
import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Entities.SectionAssessmentTool;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.SectionAssessmentToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionAssessmentToolFinder {

    @Autowired
    private SectionAssessmentToolRepository repository;
    @Autowired
    private SectionFinder sectionFinder;
    @Autowired
    private AssessmentToolFinder assessmentToolFinder;

    public SectionAssessmentTool findSectionAssessmentToolById(Long id){
        Optional<SectionAssessmentTool> sectionAssessmentTool = repository.findById(id);
        if(sectionAssessmentTool.isEmpty())
            throw new NotFound("The section_assessmentTool with id " + id + " was not found");
        return sectionAssessmentTool.get();
    }

    public List<SectionAssessmentTool> findAllSectionAssessmentToolsBySectionId(Integer courseNumber, Integer sectionNumber, Integer semester){
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber, sectionNumber, semester);
        Optional<List<SectionAssessmentTool>> sectionAssessmentTool = repository.findAllBySection(section);
        if(sectionAssessmentTool.isEmpty() || sectionAssessmentTool.get().isEmpty())
            throw new NotFound("There were no section_assessmentTools found for the course " +
                    section.getCourse().getNumber() + " and section " + section.getNumber());
        return sectionAssessmentTool.get();
    }

    public SectionAssessmentTool findSectionATByIdFromCourseNumber(Integer courseNumber, Integer sectionNumber,
                                                                   Integer semester, Long raeId,
                                                                   Long assessmentToolId,
                                                                   Long sectionAssessmentToolId){
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber, sectionNumber, semester);
        AssessmentTool assessmentTool = assessmentToolFinder
                .findAssessmentToolByCourseAndRAEId(courseNumber, raeId, assessmentToolId);
        Optional<SectionAssessmentTool> optionalSAT = repository.findById(sectionAssessmentToolId);
        if(optionalSAT.isEmpty())
            throw new NotFound("The sectionAT with id " + sectionAssessmentToolId + " was not found");
        if(!optionalSAT.get().getSection().equals(section))
            throw new DoesNotContain("Section " + sectionNumber + " from course " +
                    courseNumber + " does not contain sectionAT " + assessmentTool);
        if(!optionalSAT.get().getAssessmentTool().equals(assessmentTool))
            throw new DoesNotContain("The found assessment tool " + assessmentToolId +
                    " is not the sectionAT with id " + sectionAssessmentToolId);
        return optionalSAT.get();
    }

    public SectionAssessmentTool findSectionAssessmentToolsByAssessmentTool(AssessmentTool assessmentTool){
        Optional<SectionAssessmentTool> sectionAssessmentTool = repository.findByAssessmentTool(assessmentTool);
        if(sectionAssessmentTool.isEmpty())
            throw new NotFound("There were no section_assessmentTools found from assessmentTool" +
                    assessmentTool.getAssessmentToolId());
        return sectionAssessmentTool.get();
    }
}
