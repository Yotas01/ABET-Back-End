package edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.Entities.DTOs.PerformanceIndicatorDTO;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.AssessmentToolFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.PerformanceIndicatorFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.RAEFinder;
import edu.javeriana.abetbackend.Entities.AssessmentTool;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.PerformanceIndicator;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import edu.javeriana.abetbackend.Repositories.AssessmentToolRepository;
import edu.javeriana.abetbackend.Repositories.PerformanceIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PerformanceIndicatorCRUD {

    @Autowired
    private PerformanceIndicatorFinder performanceIndicatorFinder;
    @Autowired
    private PerformanceIndicatorRepository performanceIndicatorRepository;
    @Autowired
    private RAEFinder raeFinder;
    @Autowired
    private CourseFinder courseFinder;

    public PerformanceIndicator addPerformanceIndicator(PerformanceIndicatorDTO dto, Integer courseNumber, Long raeId){
        RAE rae = raeFinder.findRAEByCourseAndRaeId(courseNumber, raeId);
        PerformanceIndicator performanceIndicator = new PerformanceIndicator();
        performanceIndicator.setDescription(dto.getDescription());
        performanceIndicator.setPercentage(dto.getPercentage());
        rae.addPerformanceIndicator(performanceIndicator);
        performanceIndicatorRepository.save(performanceIndicator);
        return performanceIndicator;
    }

    public PerformanceIndicator updatePerformanceIndicator(PerformanceIndicatorDTO dto, Integer courseNumber, Long raeId,
                                                           Long performanceIndicatorId){
        PerformanceIndicator piToUpdate = performanceIndicatorFinder.findFromCourseRAEAndId(courseNumber, raeId, performanceIndicatorId);
        piToUpdate.setDescription(dto.getDescription());
        piToUpdate.setPercentage(dto.getPercentage());
        return piToUpdate;
    }

    public PerformanceIndicator deletePerformanceIndicator(Integer courseNumber, Long raeId, Long performanceIndicatorId){
        RAE rae = raeFinder.findRAEByCourseAndRaeId(courseNumber, raeId);
        PerformanceIndicator piToDelete = performanceIndicatorFinder.findFromCourseRAEAndId(courseNumber, raeId, performanceIndicatorId);
        rae.removePerformanceIndicator(piToDelete);
        performanceIndicatorRepository.delete(piToDelete);
        return piToDelete;
    }
}
