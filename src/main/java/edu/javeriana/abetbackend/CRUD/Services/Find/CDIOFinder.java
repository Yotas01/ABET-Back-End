package edu.javeriana.abetbackend.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Exceptions.NotFound.CDIONotFoundById;
import edu.javeriana.abetbackend.Exceptions.NotFound.CDIONotFoundByNumber;
import edu.javeriana.abetbackend.Repositories.CDIORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CDIOFinder {

    @Autowired
    private CDIORepository repository;

    public CDIO findCDIOById(Long cdioID){
        Optional<CDIO> optionalCDIO = repository.findById(cdioID);
        if (optionalCDIO.isEmpty())
            throw new CDIONotFoundById("The cdio with Id " + cdioID + " was not found");
        return optionalCDIO.get();
    }

    public CDIO findCDIOByNumber(Float cdioNumber){
        Optional<CDIO> optionalCDIO = repository.findCDIOByNumber(cdioNumber);
        if (optionalCDIO.isEmpty())
            throw new CDIONotFoundByNumber("The cdio with number " + cdioNumber + " was not found");
        return optionalCDIO.get();
    }

    public List<CDIO> getAllCDIOs(){
        return (List<CDIO>) repository.findAll();
    }
}
