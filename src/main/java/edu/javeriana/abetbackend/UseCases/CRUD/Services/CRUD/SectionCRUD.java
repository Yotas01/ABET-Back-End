package edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CourseFinder;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.SectionFinder;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Exceptions.AlreadyExists;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.CourseRepository;
import edu.javeriana.abetbackend.Repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionCRUD {

    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private SectionFinder sectionFinder;
    @Autowired
    private CourseFinder courseFinder;
    @Autowired
    private CourseRepository courseRepository;

    public void saveSection(Section section, Integer courseNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        try {
            Section sectionToCreate = sectionFinder.findSectionByNumberAndSemester(courseNumber,section.getNumber(), section.getSemester());
        }catch (NotFound exception){
            course.addSection(section);
            section.setCourse(course);
            sectionRepository.save(section);
            courseRepository.save(course);
            return;
        }
        throw new AlreadyExists("The section with the number " + section.getNumber() +
                " for the course " + course.getName() + "-" + course.getNumber() +  " already exists");
    }

    public Section updateSection(Section section, Integer courseNumber, Integer sectionNumber){
        Section sectionToUpdate = sectionFinder.findSectionByNumber(courseNumber, sectionNumber);
        sectionToUpdate.setProfessor(section.getProfessor());
        sectionToUpdate.setSemester(section.getSemester());
        sectionToUpdate.setTotalStudents(section.getTotalStudents());
        sectionRepository.save(sectionToUpdate);
        return sectionToUpdate;
    }

    public Section deleteSection(Integer sectionNumber, Integer courseNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Section sectionToDelete = sectionFinder.findSectionByNumber(courseNumber,sectionNumber);
        course.removeSection(sectionToDelete);
        sectionRepository.delete(sectionToDelete);
        courseRepository.save(course);
        return  sectionToDelete;
    }
}
