package edu.javeriana.abetbackend.UseCases.FileSystem.Controllers;


import edu.javeriana.abetbackend.Entities.DTOs.ValueDTO;
import edu.javeriana.abetbackend.UseCases.FileSystem.Services.ExcelScriptService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.script.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
@RequestMapping("/file-config")
@CrossOrigin(origins = "*")
public class CourseFileController {

    @Autowired
    private ExcelScriptService excelService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/courses/semester/{semester}")
    @ApiOperation(value = "Create all the courses from an Excel file")
    public ResponseEntity createCourses(@ApiParam(name = "file", required = true)
                                        @RequestParam MultipartFile file,
                                        @ApiParam(name = "semester", required = true)
                                        @PathVariable Integer semester){
        try{
            String realPath = request.getServletContext().getRealPath("/");
            System.out.println(realPath);
            excelService.createCoursesFromExcel(file, realPath, semester);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/sections")
    @ApiOperation(value = "Create all the section for a semester from an Excel file")
    public ResponseEntity createSections(@ApiParam(name = "file", required = true)
                                        @RequestParam MultipartFile file){
        try{
            String realPath = request.getServletContext().getRealPath("/");
            System.out.println(realPath);
            excelService.createSectionsFromExcel(file, realPath);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/get-file")
    @Operation(description = "Returns an excel file containing the information for all the courses")
    public ResponseEntity getFile() throws IOException, InterruptedException, ScriptException {
        excelService.runPython();

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }




}
