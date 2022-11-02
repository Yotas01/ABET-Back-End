package edu.javeriana.abetbackend.UseCases.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.AssessmentToolCode;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.AssessmentToolCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssessmentToolCodeFinder {

    @Autowired
    private AssessmentToolCodeRepository repository;

    public List<AssessmentToolCode> getAllCodes(){
        return (List<AssessmentToolCode>) repository.findAll();
    }

    public AssessmentToolCode getAssessmentToolCode(String code){
        Optional<AssessmentToolCode> optionalCode = repository.findById(code);
        if(optionalCode.isEmpty())
            throw new NotFound("The assessment tool code " + code + " was not found");
        return optionalCode.get();
    }
}
