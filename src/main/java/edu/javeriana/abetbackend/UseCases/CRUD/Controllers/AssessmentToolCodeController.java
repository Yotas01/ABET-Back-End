package edu.javeriana.abetbackend.UseCases.CRUD.Controllers;

import edu.javeriana.abetbackend.Entities.AssessmentToolCode;
import edu.javeriana.abetbackend.Entities.DTOs.AssessmentToolCodeListDTO;
import edu.javeriana.abetbackend.UseCases.BaseController;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.AssessmentToolCodeFinder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/codes")
@CrossOrigin(origins = "*")
public class AssessmentToolCodeController extends BaseController {

    @Autowired
    private AssessmentToolCodeFinder finder;

    @ApiOperation(value = "Get all the assessment tool codes")
    @GetMapping
    public ResponseEntity<AssessmentToolCodeListDTO> getAllCodes(){
        List<AssessmentToolCode> codes = finder.getAllCodes();
        AssessmentToolCodeListDTO dto = new AssessmentToolCodeListDTO(codes.stream().map(AssessmentToolCode::getCode).toList());
        return ResponseEntity.ok(dto);
    }
}
