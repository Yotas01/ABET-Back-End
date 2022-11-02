package edu.javeriana.abetbackend.UseCases.FileSystem.Controllers;


import edu.javeriana.abetbackend.UseCases.FileSystem.Services.ExcelScriptService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.script.*;
import java.io.*;

@RestController
@RequestMapping("/savecourses")
@CrossOrigin(origins = "*")
public class CourseFileController {

    @Autowired
    private ExcelScriptService excelService;


    @GetMapping("/get-file")
    @Operation(description = "Returns an excel file containing the information for all the courses")
    public ResponseEntity getFile() throws IOException, InterruptedException, ScriptException {
        excelService.runPython();

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }




}
