package edu.javeriana.abetbackend.CourseReview.Services.CRUD;

import edu.javeriana.abetbackend.CRUD.Services.Find.*;
import edu.javeriana.abetbackend.CourseReview.Services.Find.SectionAssessmentToolFinder;
import edu.javeriana.abetbackend.Entities.*;
import edu.javeriana.abetbackend.Exceptions.AlreadyExists;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import edu.javeriana.abetbackend.Exceptions.Inconsistent;
import edu.javeriana.abetbackend.Repositories.SectionAssessmentToolRepository;
import edu.javeriana.abetbackend.Repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionAssessmentToolCRUD {

    @Autowired
    private SectionAssessmentToolRepository repository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private SectionAssessmentToolFinder sectionAssessmentToolFinder;
    @Autowired
    private CourseFinder courseFinder;
    @Autowired
    private AssessmentToolFinder assessmentToolFinder;
    @Autowired
    private SectionFinder sectionFinder;

    public SectionAssessmentTool addSectionAssessmentTool(Integer courseNumber, Integer sectionNumber, Integer semester,
                                                          Long raeId, Long assessmentToolId,
                                                          SectionAssessmentTool sectionAssessmentTool) {
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber, sectionNumber, semester);
        AssessmentTool assessmentTool = assessmentToolFinder
                .findAssessmentToolByCourseAndRAEId(courseNumber, raeId, assessmentToolId);
        if(!section.getSemester().equals(sectionAssessmentTool.getSemester()))
            throw new DoesNotContain("The section_AT semester " + sectionAssessmentTool.getSemester() +
                " is not the same as the section semester " + section);
        Optional<SectionAssessmentTool> existing = repository.findByAssessmentToolAndSemester(assessmentTool, section.getSemester());
        if(existing.isPresent())
            throw new AlreadyExists("The section_AT for the assessment tool " + assessmentToolId +
                    " and semester " + section.getSemester() + " already exists");
        sectionAssessmentTool.setSection(section);
        sectionAssessmentTool.setAssessmentTool(assessmentTool);
        section.addSectionAssessmentTool(sectionAssessmentTool);
        repository.save(sectionAssessmentTool);
        sectionRepository.save(section);
        return sectionAssessmentTool;
    }

    public SectionAssessmentTool updateSectionAssessmentTool(Integer courseNumber, Integer sectionNumber, Integer semester,
                                                             Long raeId, Long assessmentToolId,
                                                             Long sectionAssessmentToolId,
                                                             SectionAssessmentTool sectionAssessmentTool){
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber, sectionNumber, semester);
        AssessmentTool assessmentTool = assessmentToolFinder
                .findAssessmentToolByCourseAndRAEId(courseNumber, raeId, assessmentToolId);
        if(!section.getSemester().equals(sectionAssessmentTool.getSemester()))
            throw new Inconsistent("The section_AT semester " + sectionAssessmentTool.getSemester() +
                    " is not the same as the section semester " + section.getSemester());
        SectionAssessmentTool toUpdate = sectionAssessmentToolFinder
                .findSectionATByIdFromCourseNumber(courseNumber,sectionNumber,semester,raeId,assessmentToolId,sectionAssessmentToolId);
        toUpdate.setTotalStudents(sectionAssessmentTool.getTotalStudents());
        toUpdate.setSemester(sectionAssessmentTool.getSemester());
        toUpdate.setDraft(sectionAssessmentTool.getDraft());
        repository.save(toUpdate);
        return toUpdate;
    }

    public void deleteSectionAssessmentTool(Integer courseNumber, Integer sectionNumber, Integer semester, Long raeId,
                                                             Long assessmentToolId,
                                                             Long sectionAssessmentToolId){
        SectionAssessmentTool toDelete = sectionAssessmentToolFinder
                .findSectionATByIdFromCourseNumber(courseNumber,sectionNumber,semester,raeId,assessmentToolId,sectionAssessmentToolId);
        repository.delete(toDelete);
    }
}
