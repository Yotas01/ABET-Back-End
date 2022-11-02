package edu.javeriana.abetbackend.UseCases.CRUD.Controllers;

import edu.javeriana.abetbackend.UseCases.BaseController;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD.PerformanceIndicatorCRUD;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.PerformanceIndicatorFinder;
import edu.javeriana.abetbackend.Entities.DTOs.PerformanceIndicatorDTO;
import edu.javeriana.abetbackend.Entities.PerformanceIndicator;
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
@RequestMapping("/admin/course/{courseNumber}/rae/{raeId}/performanceIndicator")
@CrossOrigin(origins = "*")
public class PerformanceIndicatorCRUDController extends BaseController {

    @Autowired
    private PerformanceIndicatorCRUD performanceIndicatorService;
    @Autowired
    private PerformanceIndicatorFinder performanceIndicatorFinder;

    @ApiOperation(value = "Add a performance indicator to a RAE")
    @PostMapping
    public ResponseEntity<PerformanceIndicatorDTO> addPerformanceIndicator(@ApiParam(name = "courseNumber", required = true)
                                                                               @PathVariable Integer courseNumber,
                                                                           @ApiParam(name = "raeId", required = true)
                                                                           @PathVariable Long raeId,
                                                                     @RequestBody PerformanceIndicatorDTO dto){
        PerformanceIndicator performanceIndicator = performanceIndicatorService.addPerformanceIndicator(dto,courseNumber,raeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PerformanceIndicatorDTO(performanceIndicator));
    }

    @ApiOperation(value = "Update a performance indicator")
    @PutMapping("/{performanceIndicatorId}")
    public ResponseEntity<PerformanceIndicatorDTO> updatePerformanceIndicator(@ApiParam(name = "courseNumber", required = true)
                                                                                  @PathVariable Integer courseNumber,
                                                                              @ApiParam(name = "raeId", required = true)
                                                                                  @PathVariable Long raeId,
                                                                              @ApiParam(name = "performanceIndicatorId", required = true)
                                                                                  @PathVariable Long performanceIndicatorId,
                                                                              @RequestBody PerformanceIndicatorDTO performanceIndicator){
        PerformanceIndicator performanceIndicatorToUpdate = performanceIndicatorService
                .updatePerformanceIndicator(performanceIndicator,courseNumber,raeId,performanceIndicatorId);
        return ResponseEntity.status(HttpStatus.OK).body(new PerformanceIndicatorDTO(performanceIndicatorToUpdate));
    }

    @ApiOperation(value = "Delete a performance indicator")
    @DeleteMapping("/{performanceIndicatorId}")
    public ResponseEntity<PerformanceIndicatorDTO> deletePerformanceIndicator(@ApiParam(name = "courseNumber", required = true)
                                                                                  @PathVariable Integer courseNumber,
                                                                              @ApiParam(name = "raeId", required = true)
                                                                                  @PathVariable Long raeId,
                                                                              @ApiParam(name = "performanceIndicatorId", required = true)
                                                                                  @PathVariable Long performanceIndicatorId){
        PerformanceIndicator pi = performanceIndicatorService
                .deletePerformanceIndicator(courseNumber, raeId, performanceIndicatorId);
        return ResponseEntity.status(HttpStatus.OK).body(new PerformanceIndicatorDTO(pi));
    }

    @ApiOperation(value = "Get a performance indicator")
    @GetMapping("/{performanceIndicatorId}")
    public ResponseEntity<PerformanceIndicatorDTO> getPerformanceIndicator(@ApiParam(name = "courseNumber", required = true)
                                                                               @PathVariable Integer courseNumber,
                                                                           @ApiParam(name = "raeId", required = true)
                                                                               @PathVariable Long raeId,
                                                                           @ApiParam(name = "performanceIndicatorId", required = true)
                                                                               @PathVariable Long performanceIndicatorId){
        PerformanceIndicator pi = performanceIndicatorFinder.findFromCourseRAEAndId(courseNumber,raeId, performanceIndicatorId);
        return ResponseEntity.status(HttpStatus.OK).body(new PerformanceIndicatorDTO(pi));
    }
    @Operation(summary = "Get all the performance indicators from a RAE")
    @GetMapping
    public ResponseEntity<List<PerformanceIndicatorDTO>> getPerformanceIndicators(@ApiParam(name = "courseNumber", required = true)
                                                                                @PathVariable Integer courseNumber,
                                                                            @ApiParam(name = "raeId", required = true)
                                                                                @PathVariable Long raeId){
        List<PerformanceIndicator> piList = performanceIndicatorFinder.getAllByRAE(courseNumber, raeId);
        List<PerformanceIndicatorDTO> dtoList = new ArrayList<>(piList.stream().map(PerformanceIndicatorDTO::new).toList());
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }
}
