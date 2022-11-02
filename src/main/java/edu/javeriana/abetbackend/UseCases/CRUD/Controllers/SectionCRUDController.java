package edu.javeriana.abetbackend.UseCases.CRUD.Controllers;

import edu.javeriana.abetbackend.UseCases.BaseController;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD.SectionCRUD;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.SectionFinder;
import edu.javeriana.abetbackend.Entities.DTOs.NameDTO;
import edu.javeriana.abetbackend.Entities.DTOs.SectionDTO;
import edu.javeriana.abetbackend.Entities.Section;
import edu.javeriana.abetbackend.Exceptions.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/course")
@CrossOrigin(origins = "*")
public class SectionCRUDController extends BaseController {

    @Autowired
    private SectionCRUD sectionCRUDService;
    @Autowired
    private SectionFinder sectionFinder;

    @ApiOperation(value = "Add a new section to an existing course")
    @PostMapping("/{courseNumber}/section")
    public ResponseEntity<SectionDTO> addSection(@ApiParam(name = "courseNumber", required = true)
                                                     @PathVariable Integer courseNumber,
                                                 @RequestBody SectionDTO sectionDTO){
        Section section = sectionCRUDService.createSection(sectionDTO, courseNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SectionDTO(section));
    }

    @ApiOperation(value = "Find a section corresponding to the course, sectionNumber and the semester")
    @GetMapping("/{courseNumber}/section/{sectionNumber}/semester/{semester}")
    public ResponseEntity<SectionDTO> findSectionByNumberAndSemester(@ApiParam(name = "sectionNumber", required = true)
                                                                         @PathVariable Integer sectionNumber,
                                                                     @ApiParam(name = "courseNumber", required = true)
                                                                     @PathVariable Integer courseNumber,
                                                                     @ApiParam(name = "semester", required = true)
                                                                         @PathVariable Integer semester){
        Section section = sectionFinder.findSectionByNumberAndSemester(courseNumber, sectionNumber, semester);
        return ResponseEntity.status(HttpStatus.OK).body(new SectionDTO(section));
    }

    @ApiOperation(value = "Find the sections whose professor is the professorName")
    @GetMapping("/sectionByProfessor/semester/{semester}")
    public ResponseEntity<List<SectionDTO>> findSectionByProfessor(@RequestBody NameDTO name,
                                                                   @ApiParam(name = "semester", required = true)
                                                                   @PathVariable Integer semester){
        List<Section> sections = sectionFinder.findByProfessor(name.getName(), semester);
        List<SectionDTO> sectionDTOs = new ArrayList<>();
        sections.forEach(section -> sectionDTOs.add(new SectionDTO(section)));
        return ResponseEntity.status(HttpStatus.OK).body(sectionDTOs);
    }

    @ApiOperation(value = "Find the sections from a Course and semester")
    @GetMapping("/{courseNumber}/sections/semester/{semester}")
    public ResponseEntity<List<SectionDTO>> findCourseSectionsBySemester(@ApiParam(name = "courseName", required = true)
                                                                             @PathVariable Integer courseNumber,
                                                                         @ApiParam(name = "semester", required = true)
                                                                         @PathVariable Integer semester){
        List<Section> sections = sectionFinder.findSectionsFromCourseNumberAndSemester(courseNumber, semester);
        List<SectionDTO> sectionDTOs = new ArrayList<>();
        sections.forEach(section -> sectionDTOs.add(new SectionDTO(section)));
        return ResponseEntity.status(HttpStatus.OK).body(sectionDTOs);
    }

    @ApiOperation(value = "Update a section that matches the section's id")
    @PutMapping("/{courseNumber}/section/{sectionNumber}/semester/{semester}")
    public ResponseEntity<SectionDTO> updateCourse(@RequestBody SectionDTO section,
                                                   @ApiParam(name = "courseNumber", required = true)
                                                   @PathVariable Integer courseNumber,
                                                   @ApiParam(name = "sectionNumber", required = true)
                                                       @PathVariable Integer sectionNumber,
                                                   @ApiParam(name = "semester", required = true)
                                                   @PathVariable Integer semester){
        Section updatedSection = sectionCRUDService.updateSection(section,courseNumber, sectionNumber, semester);
        SectionDTO responseCourse = new SectionDTO(updatedSection);
        return ResponseEntity.status(HttpStatus.OK).body(responseCourse);
    }

    @ApiOperation(value = "Delete the section that matches the section number")
    @DeleteMapping("/{courseNumber}/section/{sectionNumber}/semester/{semester}")
    public ResponseEntity<SectionDTO> deleteCourseByNumber(@PathVariable(value = "courseNumber") Integer courseNumber,
                                                           @PathVariable(value = "sectionNumber") Integer sectionNumber,
                                                           @ApiParam(name = "semester", required = true)
                                                               @PathVariable Integer semester){
        Section sectionToDelete = sectionCRUDService.deleteSection(sectionNumber, courseNumber, semester);
        return ResponseEntity.status(HttpStatus.OK).body(new SectionDTO(sectionToDelete));
    }
}
