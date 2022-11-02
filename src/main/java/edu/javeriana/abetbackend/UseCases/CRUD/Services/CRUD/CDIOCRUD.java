package edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.Entities.DTOs.CDIODTO;
import edu.javeriana.abetbackend.UseCases.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Exceptions.AlreadyExists;
import edu.javeriana.abetbackend.Exceptions.NotFound;
import edu.javeriana.abetbackend.Repositories.CDIORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CDIOCRUD {

    @Autowired
    private CDIORepository repository;
    @Autowired
    private CDIOFinder finder;

    public CDIO createCDIO(CDIODTO dto) {
        try {
            CDIO existingCDIO =  finder.findCDIOById(dto.getNumber());
        }catch (NotFound exception){
            CDIO cdio = new CDIO(dto.getDescription(), dto.getNumber());
            repository.save(cdio);
            return cdio;
        }
        throw new AlreadyExists("The CDIO with number " + dto.getNumber() + " already exists");
    }

    public CDIO updateCDIO(CDIODTO dto, Float cdioNumber){
        CDIO updatedCDIO = finder.findCDIOById(cdioNumber);
        updatedCDIO.setDescription(dto.getDescription());
        updatedCDIO.setNumber(dto.getNumber());
        repository.save(updatedCDIO);
        return updatedCDIO;
    }

    public CDIO deleteCDIO(Float cdioNumber){
        CDIO cdioToDelete = finder.findCDIOById(cdioNumber);
        repository.delete(cdioToDelete);
        return cdioToDelete;
    }
}
