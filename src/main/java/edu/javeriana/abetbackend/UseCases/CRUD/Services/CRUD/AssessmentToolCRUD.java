package edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.DTOs.AssessmentToolDTO;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.AssessmentToolFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Repositories.AssessmentToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessmentToolCRUD {

    @Autowired
    private AssessmentToolRepository assessmentToolRepository;
    @Autowired
    private AssessmentToolFinder assessmentToolFinder;
    @Autowired
    private CourseFinder courseFinder;

    public AssessmentTool saveAssessmentTool(AssessmentToolDTO dto, Integer courseNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        AssessmentTool assessmentTool = new AssessmentTool();
        assessmentTool.setCategory(dto.getCategory());
        assessmentTool.setCourse(course);
        assessmentTool.setSemester(dto.getSemester());
        assessmentTool.setDescription(dto.getDescription());
        assessmentTool.setValue(dto.getValue());
        course.addAssessmentTool(assessmentTool);
        assessmentToolRepository.save(assessmentTool);
        return assessmentTool;
    }

    public AssessmentTool updateAssessmentTool(AssessmentToolDTO dto, Integer courseNumber, Long assessmentToolId){
        AssessmentTool assessmentToolToUpdate = assessmentToolFinder.findByCourseAndId(courseNumber, assessmentToolId);
        assessmentToolToUpdate.setCategory(dto.getCategory());
        assessmentToolToUpdate.setSemester(dto.getSemester());
        assessmentToolToUpdate.setDescription(dto.getDescription());
        assessmentToolToUpdate.setValue(dto.getValue());
        assessmentToolRepository.save(assessmentToolToUpdate);
        return assessmentToolToUpdate;
    }

    public AssessmentTool deleteAssessmentTool(Long assessmentToolId, Integer courseNumber){
        AssessmentTool assessmentToolToDelete = assessmentToolFinder.findByCourseAndId(courseNumber, assessmentToolId);
        assessmentToolRepository.delete(assessmentToolToDelete);
        return assessmentToolToDelete;
    }
}
