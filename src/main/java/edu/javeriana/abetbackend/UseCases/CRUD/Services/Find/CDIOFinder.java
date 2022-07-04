package edu.javeriana.abetbackend.UseCases.CRUD.Services.Find;

import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.CDIORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CDIOFinder {

    @Autowired
    private CDIORepository repository;

    public CDIO findCDIOById(Float cdioNumber){
        Optional<CDIO> optionalCDIO = repository.findById(cdioNumber);
        if (optionalCDIO.isEmpty())
            throw new NotFound("The cdio with number " + cdioNumber + " was not found");
        return optionalCDIO.get();
    }

    public List<CDIO> getAllCDIOs(){
        return (List<CDIO>) repository.findAll();
    }
}
