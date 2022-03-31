package edu.javeriana.abetbackend.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.CRUD.Services.Find.AssessmentToolFinder;
import edu.javeriana.abetbackend.CRUD.Services.Find.RAEFinder;
import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Repositories.AssessmentToolRepository;
import edu.javeriana.abetbackend.Repositories.RAERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssessmentToolCRUD {

    @Autowired
    private AssessmentToolRepository assessmentToolRepository;
    @Autowired
    private AssessmentToolFinder assessmentToolFinder;
    @Autowired
    private RAEFinder raeFinder;
    @Autowired
    private RAERepository raeRepository;

    public void saveAssessmentTool(AssessmentTool assessmentTool, Long raeId, Integer courseNumber){
        RAE rae = raeFinder.findRAEByCourseAndRaeId(courseNumber, raeId);
        rae.addAssessmentTool(assessmentTool);
        assessmentTool.setRae(rae);
        assessmentToolRepository.save(assessmentTool);
        raeRepository.save(rae);
    }

    public AssessmentTool updateAssessmentTool(AssessmentTool assessmentTool, Long raeId, Integer courseNumber, Long assessmentToolId){
        RAE rae = raeFinder.findRAEByCourseAndRaeId(courseNumber, raeId);
        AssessmentTool assessmentToolToUpdate = assessmentToolFinder
                .findAssessmentToolByCourseAndRAEId(courseNumber, raeId, assessmentToolId);
        assessmentToolToUpdate.setRae(rae);
        assessmentToolToUpdate.setDescription(assessmentTool.getDescription());
        assessmentToolToUpdate.setValue(assessmentTool.getValue());
        assessmentToolToUpdate.setPerformanceIndicators(new ArrayList<>(assessmentTool.getPerformanceIndicators()));
        assessmentToolRepository.save(assessmentToolToUpdate);
        return assessmentToolToUpdate;
    }

    public AssessmentTool deleteAssessmentTool(Long assessmentToolId, Long raeId, Integer courseNumber){
        RAE rae = raeFinder.findRAEByCourseAndRaeId(courseNumber, raeId);
        AssessmentTool assessmentToolToDelete = assessmentToolFinder
                .findAssessmentToolByCourseAndRAEId(courseNumber, raeId, assessmentToolId);
        rae.removeAssessmentTool(assessmentToolToDelete);
        assessmentToolRepository.delete(assessmentToolToDelete);
        raeRepository.save(rae);
        return assessmentToolToDelete;
    }
}
