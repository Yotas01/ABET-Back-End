package edu.javeriana.abetbackend.UseCases.CRUD.Services.CRUD;

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

    public void saveCDIO(CDIO cdio) {
        try {
            CDIO existingCDIO =  finder.findCDIOById(cdio.getNumber());
        }catch (NotFound exception){
            repository.save(cdio);
            return;
        }
        throw new AlreadyExists("The CDIO with number " + cdio.getNumber() + " already exists");
    }

    public CDIO updateCDIO(CDIO cdio){
        CDIO updatedCDIO = finder.findCDIOById(cdio.getNumber());
        updatedCDIO.setDescription(cdio.getDescription());
        updatedCDIO.setNumber(cdio.getNumber());
        updatedCDIO.setOutcomes(new ArrayList<>(cdio.getOutcomes()));
        updatedCDIO.setRAEs(new ArrayList<>(cdio.getRAEs()));
        updatedCDIO.setCourses(new ArrayList<>(cdio.getCourses()));
        repository.save(updatedCDIO);
        return updatedCDIO;
    }

    public CDIO deleteCDIO(Float cdioNumber){
        CDIO cdioToDelete = finder.findCDIOById(cdioNumber);
        repository.delete(cdioToDelete);
        return cdioToDelete;
    }
}
