package edu.javeriana.abetbackend.CRUD.Controllers;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.SectionCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.SectionFinder;
import edu.javeriana.abetbackend.Entities.ResponseEntities.ResponseSection;
import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Exceptions.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class SectionCRUDController {

    @Autowired
    private SectionCRUD sectionCRUDService;
    @Autowired
    private SectionFinder sectionFinder;

    @Operation(summary = "Create a new Section")
    @PostMapping("/section")
    public ResponseEntity<ResponseSection> addSection(@RequestBody Section section){
        sectionCRUDService.saveSection(section);
        ResponseSection responseSection = new ResponseSection(section);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseSection);
    }

    @Operation(summary = "Find the section with sectionNumber")
    @GetMapping("/section/{sectionNumber}")
    public ResponseEntity<ResponseSection> findSectionByNumber(@PathVariable(value = "sectionNumber") Integer number){
        Section section = sectionFinder.findSectionByNumber(number);
        ResponseSection responseSection = new ResponseSection(section);
        return ResponseEntity.status(HttpStatus.OK).body(responseSection);
    }

    @Operation(summary = "Find the sections whose professor is the professorName")
    @GetMapping("/section/find/{professorName}")
    public ResponseEntity<List<ResponseSection>> findSectionByProfessor(@PathVariable(value = "professorName") String name){
        List<Section> sections = sectionFinder.findByProfessor(name);
        List<ResponseSection> responseSections = new ArrayList<>();
        sections.forEach(section -> responseSections.add(new ResponseSection(section)));
        return ResponseEntity.status(HttpStatus.OK).body(responseSections);
    }

    @Operation(summary = "Find the sections from a Course")
    @GetMapping("/section/find/{courseNumber}")
    public ResponseEntity<List<ResponseSection>> findCourseSections(@PathVariable(value = "courseNumber") Integer number){
        List<Section> sections = sectionFinder.findSectionsFromCourseNumber(number);
        List<ResponseSection> responseSections = new ArrayList<>();
        sections.forEach(section -> responseSections.add(new ResponseSection(section)));
        return ResponseEntity.status(HttpStatus.OK).body(responseSections);
    }

    @Operation(summary = "Get all the sections")
    @GetMapping("/section")
    public ResponseEntity<List<ResponseSection>> getAllCourses(){
        List<Section> sections = sectionFinder.getAllSections();
        List<ResponseSection> responseSections = new ArrayList<>();
        sections.forEach(section -> responseSections.add(new ResponseSection(section)));
        return ResponseEntity.status(HttpStatus.OK).body(responseSections);
    }

    @Operation(summary = "Update a section that matches the section's id")
    @PutMapping("/section")
    public ResponseEntity<ResponseSection> updateCourse(@RequestBody Section section){
        Section updatedSection = sectionCRUDService.updateSection(section);
        ResponseSection responseCourse = new ResponseSection(updatedSection);
        return ResponseEntity.status(HttpStatus.OK).body(responseCourse);
    }

    @Operation(summary = "Delete the section that matches the section number")
    @DeleteMapping("/section/{sectionNumber}")
    public ResponseEntity<ResponseSection> deleteCourseByNumber(@PathVariable(value = "sectionNumber") Integer number){
        Section sectionToDelete = sectionFinder.findSectionByNumber(number);
        Section deletedSection = sectionCRUDService.deleteSection(sectionToDelete);
        ResponseSection responseCourse = new ResponseSection(deletedSection);
        return ResponseEntity.status(HttpStatus.OK).body(responseCourse);
    }

    @ExceptionHandler({SectionNotFound.class, SectionNotFoundByProfessor.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundError(Exception exception){
        return exception.getMessage();
    }

    @ExceptionHandler(SectionAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String alreadyExistsError(Exception exception){
        return exception.getMessage();
    }
}
