package edu.javeriana.abetbackend.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Exceptions.NotFound.AssessmentToolNotFoundById;
import edu.javeriana.abetbackend.Exceptions.NotFound.AssessmentToolsNotFoundByRAE;
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
            throw new AssessmentToolNotFoundById("The assessment tool with id " + id + " was not found");
        return assessmentTool.get();
    }

    public List<AssessmentTool> getAssessmentToolsByRAEId(Long raeId){
        RAE rae = raeFinder.findRAEById(raeId);
        Optional<List<AssessmentTool>> assessmentTools = repository.findAssessmentToolByRae(rae);
        if (assessmentTools.isEmpty() || assessmentTools.get().isEmpty())
            throw new AssessmentToolsNotFoundByRAE("The assessment tools from the rae " + rae.getRAEId()
                    + " were not found");
        return assessmentTools.get();
    }
}
