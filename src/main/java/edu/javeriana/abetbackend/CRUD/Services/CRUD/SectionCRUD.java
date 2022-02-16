package edu.javeriana.abetbackend.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.CRUD.Services.Find.SectionFinder;
import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Exceptions.SectionAlreadyExists;
import edu.javeriana.abetbackend.Exceptions.SectionNotFound;
import edu.javeriana.abetbackend.Repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionCRUD {

    @Autowired
    private SectionRepository repository;
    @Autowired
    private SectionFinder sectionFinder;

    public void saveSection(Section section){
        try {
            Section sectionToCreate = sectionFinder.findSectionByNumber(section.getNumber());
        }catch (SectionNotFound exception){
            repository.save(section);
            return;
        }
        throw new SectionAlreadyExists("The section with the number " + section.getNumber() + " already exists");
    }

    public Section updateSection(Section section){
        Section sectionToUpdate = sectionFinder.findSectionByNumber(section.getNumber());
        sectionToUpdate.setProfessor(section.getProfessor());
        sectionToUpdate.setSemester(section.getSemester());
        sectionToUpdate.setTotalStudents(section.getTotalStudents());
        repository.save(sectionToUpdate);
        return sectionToUpdate;
    }

    public Section deleteSection(Section section){
        Section sectionToDelete = sectionFinder.findSectionById(section.getSectionId());
        repository.delete(sectionToDelete);
        return  sectionToDelete;
    }
}
