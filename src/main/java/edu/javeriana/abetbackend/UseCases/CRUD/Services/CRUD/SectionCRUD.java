package edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.Entities.DTOs.SectionDTO;
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

    public Section createSection(SectionDTO dto, Integer courseNumber){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        try {
            Section sectionToCreate = sectionFinder.findSectionByNumberAndSemester(courseNumber, dto.getClassNumber(), dto.getSemester());
        }catch (NotFound exception){
            Section section = new Section(dto.getSectionId(), course, dto.getClassNumber(), dto.getSemester(), dto.getProfessor(), dto.getTotalStudents());
            course.addSection(section);
            sectionRepository.save(section);
            courseRepository.save(course);
            return section;
        }
        throw new AlreadyExists("The section with the number " + dto.getClassNumber() +
                " for the course " + course.getName() + "-" + course.getCourseId() +  " already exists");
    }

    public Section updateSection(SectionDTO section, Integer courseNumber, Integer sectionNumber, Integer semester){
        Section sectionToUpdate = sectionFinder.findSectionByNumberAndSemester(courseNumber, sectionNumber, semester);
        sectionToUpdate.setSectionId(section.getSectionId());
        sectionToUpdate.setProfessor(section.getProfessor());
        sectionToUpdate.setSemester(section.getSemester());
        sectionToUpdate.setTotalStudents(section.getTotalStudents());
        sectionRepository.save(sectionToUpdate);
        return sectionToUpdate;
    }

    public Section deleteSection(Integer sectionNumber, Integer courseNumber, Integer semester){
        Course course = courseFinder.findCourseByNumber(courseNumber);
        Section sectionToDelete = sectionFinder.findSectionByNumberAndSemester(courseNumber, sectionNumber, semester);
        course.removeSection(sectionToDelete);
        sectionRepository.delete(sectionToDelete);
        courseRepository.save(course);
        return  sectionToDelete;
    }
}
