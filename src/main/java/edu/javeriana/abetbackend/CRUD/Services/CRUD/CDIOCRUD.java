package edu.javeriana.abetbackend.CRUD.Services.CRUD;

import edu.javeriana.abetbackend.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Exceptions.AlreadyExists.CDIOAlreadyExists;
import edu.javeriana.abetbackend.Exceptions.NotFound.CDIONotFoundByNumber;
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
            CDIO existingCDIO =  finder.findCDIOByNumber(cdio.getNumber());
        }catch (CDIONotFoundByNumber exception){
            repository.save(cdio);
            return;
        }
        throw new CDIOAlreadyExists("The CDIO with number " + cdio.getNumber() + " already exists");
    }

    public CDIO updateCDIO(CDIO cdio){
        CDIO updatedCDIO = finder.findCDIOByNumber(cdio.getNumber());
        updatedCDIO.setDescription(cdio.getDescription());
        updatedCDIO.setNumber(cdio.getNumber());
        updatedCDIO.setOutcomes(new ArrayList<>(cdio.getOutcomes()));
        updatedCDIO.setRAEs(new ArrayList<>(cdio.getRAEs()));
        updatedCDIO.setCourses(new ArrayList<>(cdio.getCourses()));
        repository.save(updatedCDIO);
        return updatedCDIO;
    }

    public CDIO deleteCDIO(Float cdioNumber){
        CDIO cdioToDelete = finder.findCDIOByNumber(cdioNumber);
        repository.delete(cdioToDelete);
        return cdioToDelete;
    }
}
