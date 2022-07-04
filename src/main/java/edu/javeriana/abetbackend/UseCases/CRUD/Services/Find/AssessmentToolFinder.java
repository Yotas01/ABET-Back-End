package edu.javeriana.abetbackend.UseCases.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.AssessmentToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssessmentToolFinder {

    @Autowired
    private AssessmentToolRepository repository;
    @Autowired
    private RAEFinder raeFinder;

    public AssessmentTool findById(Long id){
        Optional<AssessmentTool> assessmentTool = repository.findById(id);
        if (assessmentTool.isEmpty())
            throw new NotFound("The assessment tool with id " + id + " was not found");
        return assessmentTool.get();
    }

    public List<AssessmentTool> findAssessmentToolsByRAEId(Long raeId){
        RAE rae = raeFinder.findRAEById(raeId);
        Optional<List<AssessmentTool>> assessmentTools = repository.findAllByRae(rae);
        if (assessmentTools.isEmpty() || assessmentTools.get().isEmpty())
            throw new NotFound("The assessment tools from the rae " + rae.getRAEId()
                    + " were not found");
        return assessmentTools.get();
    }

    public AssessmentTool findAssessmentToolByCourseAndRAEId(Integer courseNumber, Long raeId, Long assessmentToolId){
        RAE rae = raeFinder.findRAEByCourseAndRaeId(courseNumber, raeId);
        Optional<AssessmentTool> assessmentTool = repository.findById(assessmentToolId);
        if (assessmentTool.isEmpty())
            throw new NotFound("The assessment tool with id " + assessmentToolId + " was not found");
        if(!assessmentTool.get().getRae().equals(rae))
            throw new DoesNotContain("The rae " + raeId + " does not contain the assessment tool"
            + assessmentToolId);
        return assessmentTool.get();
    }
}
