package edu.javeriana.abetbackend.CRUD.Controllers;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.SectionCRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.SectionFinder;
import edu.javeriana.abetbackend.Entities.DTOs.NameDTO;
import edu.javeriana.abetbackend.Entities.DTOs.SectionDTO;
import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Exceptions.AlreadyExists.SectionAlreadyExists;
import edu.javeriana.abetbackend.Exceptions.NotFound.CourseNotFoundById;
import edu.javeriana.abetbackend.Exceptions.NotFound.SectionNotFound;
import edu.javeriana.abetbackend.Exceptions.NotFound.SectionNotFoundByProfessor;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/course")
public class SectionCRUDController {

    @Autowired
    private SectionCRUD sectionCRUDService;
    @Autowired
    private SectionFinder sectionFinder;

    @Operation(summary = "Create a new Section")
    @PostMapping("/{courseNumber}/section")
    public ResponseEntity<SectionDTO> addSection(@PathVariable(value = "courseNumber") Integer courseNumber,
                                                 @RequestBody Section section){
        sectionCRUDService.saveSection(section, courseNumber);
        SectionDTO sectionDTO = new SectionDTO(section);
        return ResponseEntity.status(HttpStatus.CREATED).body(sectionDTO);
    }

    @Operation(summary = "Find the section with sectionNumber")
    @GetMapping("/{courseNumber}/section/{sectionNumber}")
    public ResponseEntity<SectionDTO> findSectionByNumber(@PathVariable(value = "sectionNumber") Integer sectionNumber,
                                                          @PathVariable(value = "courseNumber") Integer courseNumber){
        Section section = sectionFinder.findSectionByNumber(courseNumber, sectionNumber);
        SectionDTO sectionDTO = new SectionDTO(section);
        return ResponseEntity.status(HttpStatus.OK).body(sectionDTO);
    }

    @Operation(summary = "Find the sections whose professor is the professorName")
    @GetMapping("/sectionByProfessor")
    public ResponseEntity<List<SectionDTO>> findSectionByProfessor(@RequestBody NameDTO name){
        List<Section> sections = sectionFinder.findByProfessor(name.getName());
        List<SectionDTO> sectionDTOs = new ArrayList<>();
        sections.forEach(section -> sectionDTOs.add(new SectionDTO(section)));
        return ResponseEntity.status(HttpStatus.OK).body(sectionDTOs);
    }

    @Operation(summary = "Find the sections from a Course")
    @GetMapping("/{courseNumber}/sections")
    public ResponseEntity<List<SectionDTO>> findCourseSections(@PathVariable(value = "courseNumber") Integer courseNumber){
        List<Section> sections = sectionFinder.findSectionsFromCourseNumber(courseNumber);
        List<SectionDTO> sectionDTOs = new ArrayList<>();
        sections.forEach(section -> sectionDTOs.add(new SectionDTO(section)));
        return ResponseEntity.status(HttpStatus.OK).body(sectionDTOs);
    }

    @Operation(summary = "Get all the sections")
    @GetMapping("/section")
    public ResponseEntity<List<SectionDTO>> getAllCourses(){
        List<Section> sections = sectionFinder.getAllSections();
        List<SectionDTO> sectionDTOs = new ArrayList<>();
        sections.forEach(section -> sectionDTOs.add(new SectionDTO(section)));
        return ResponseEntity.status(HttpStatus.OK).body(sectionDTOs);
    }

    @Operation(summary = "Update a section that matches the section's id")
    @PutMapping("/{courseNumber}/section/{sectionNumber}")
    public ResponseEntity<SectionDTO> updateCourse(@RequestBody Section section,
                                                   @PathVariable(value = "courseNumber") Integer courseNumber,
                                                   @PathVariable(value = "sectionNumber") Integer sectionNumber){
        Section updatedSection = sectionCRUDService.updateSection(section,courseNumber, sectionNumber);
        SectionDTO responseCourse = new SectionDTO(updatedSection);
        return ResponseEntity.status(HttpStatus.OK).body(responseCourse);
    }

    @Operation(summary = "Delete the section that matches the section number")
    @DeleteMapping("/{courseNumber}/section/{sectionNumber}")
    public ResponseEntity<SectionDTO> deleteCourseByNumber(@PathVariable(value = "courseNumber") Integer courseNumber,
                                                           @PathVariable(value = "sectionNumber") Integer sectionNumber){
        Section sectionToDelete = sectionCRUDService.deleteSection(sectionNumber, courseNumber);
        SectionDTO responseCourse = new SectionDTO(sectionToDelete);
        return ResponseEntity.status(HttpStatus.OK).body(responseCourse);
    }

    @ExceptionHandler({CourseNotFoundById.class , SectionNotFound.class, SectionNotFoundByProfessor.class})
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
