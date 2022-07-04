package edu.javeriana.abetbackend.UseCases.CRUD.Controllers;

import edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD.SectionCRUD;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.SectionFinder;
import edu.javeriana.abetbackend.Common.Constants;
import edu.javeriana.abetbackend.Entities.DTOs.NameDTO;
import edu.javeriana.abetbackend.Entities.DTOs.SectionDTO;
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
@RequestMapping(value = "/admin/course")
public class SectionCRUDController {

    @Autowired
    private SectionCRUD sectionCRUDService;
    @Autowired
    private SectionFinder sectionFinder;

    @Operation(summary = "Create a new Section")
    @PostMapping("/{courseNumber}/section")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<SectionDTO> addSection(@PathVariable(value = "courseNumber") Integer courseNumber,
                                                 @RequestBody Section section){
        sectionCRUDService.saveSection(section, courseNumber);
        SectionDTO sectionDTO = new SectionDTO(section);
        return ResponseEntity.status(HttpStatus.CREATED).body(sectionDTO);
    }

    @Operation(summary = "Find the section with sectionNumber and the semester")
    @GetMapping("/{courseNumber}/section/{sectionNumber}/semester/{semester}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<SectionDTO> findSectionByNumberAndSemester(@PathVariable(value = "sectionNumber") Integer sectionNumber,
                                                                     @PathVariable(value = "courseNumber") Integer courseNumber,
                                                                     @PathVariable(value = "semester") Integer semester){
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber, sectionNumber, semester);
        SectionDTO sectionDTO = new SectionDTO(section);
        return ResponseEntity.status(HttpStatus.OK).body(sectionDTO);
    }

    @Operation(summary = "Find the sections whose professor is the professorName")
    @GetMapping("/sectionByProfessor")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<List<SectionDTO>> findSectionByProfessor(@RequestBody NameDTO name){
        List<Section> sections = sectionFinder.findByProfessor(name.getName());
        List<SectionDTO> sectionDTOs = new ArrayList<>();
        sections.forEach(section -> sectionDTOs.add(new SectionDTO(section)));
        return ResponseEntity.status(HttpStatus.OK).body(sectionDTOs);
    }

    @Operation(summary = "Find the sections from a Course and semester")
    @GetMapping("/{courseNumber}/sections/semester/{semester}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<List<SectionDTO>> findCourseSectionsBySemester(@PathVariable(value = "courseNumber") Integer courseNumber,
                                                                         @PathVariable(value = "semester") Integer semester){
        List<Section> sections = sectionFinder.findSectionsFromCourseNumberAndSemester(courseNumber, semester);
        List<SectionDTO> sectionDTOs = new ArrayList<>();
        sections.forEach(section -> sectionDTOs.add(new SectionDTO(section)));
        return ResponseEntity.status(HttpStatus.OK).body(sectionDTOs);
    }

    @Operation(summary = "Update a section that matches the section's id")
    @PutMapping("/{courseNumber}/section/{sectionNumber}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<SectionDTO> updateCourse(@RequestBody Section section,
                                                   @PathVariable(value = "courseNumber") Integer courseNumber,
                                                   @PathVariable(value = "sectionNumber") Integer sectionNumber){
        Section updatedSection = sectionCRUDService.updateSection(section,courseNumber, sectionNumber);
        SectionDTO responseCourse = new SectionDTO(updatedSection);
        return ResponseEntity.status(HttpStatus.OK).body(responseCourse);
    }

    @Operation(summary = "Delete the section that matches the section number")
    @DeleteMapping("/{courseNumber}/section/{sectionNumber}")
    @CrossOrigin(origins = Constants.crossOriginLocalhost)
    public ResponseEntity<SectionDTO> deleteCourseByNumber(@PathVariable(value = "courseNumber") Integer courseNumber,
                                                           @PathVariable(value = "sectionNumber") Integer sectionNumber){
        Section sectionToDelete = sectionCRUDService.deleteSection(sectionNumber, courseNumber);
        SectionDTO responseCourse = new SectionDTO(sectionToDelete);
        return ResponseEntity.status(HttpStatus.OK).body(responseCourse);
    }

    @ExceptionHandler({DoesNotContain.class, Inconsistent.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ABETSystemException badRequestError(Exception e){ return  new ABETSystemException(e.getMessage(), 400);}

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ABETSystemException notFoundError(Exception e){ return  new ABETSystemException(e.getMessage(), 404);}

    @ExceptionHandler({AlreadyContains.class, AlreadyExists.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ABETSystemException conflictError(Exception e){ return  new ABETSystemException(e.getMessage(), 409);}
}
