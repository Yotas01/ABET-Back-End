package edu.javeriana.abetbackend.CRUD.Services.Relations;

import edu.javeriana.abetbackend.CRUD.Services.CRUD.CDIOCRUD;
import edu.javeriana.abetbackend.CRUD.Services.CRUD.RAECRUD;
import edu.javeriana.abetbackend.CRUD.Services.Find.CDIOFinder;
import edu.javeriana.abetbackend.CRUD.Services.Find.RAEFinder;
import edu.javeriana.abetbackend.Entities.CDIO;
import edu.javeriana.abetbackend.Entities.RAE;
import edu.javeriana.abetbackend.Exceptions.AlreadyContains;
import edu.javeriana.abetbackend.Exceptions.DoesNotContain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RAE_CDIORelationsService {

    @Autowired
    private RAECRUD raeService;
    @Autowired
    private RAEFinder raeFinder;
    @Autowired
    private CDIOCRUD cdioService;
    @Autowired
    private CDIOFinder cdioFinder;

    public void addRAEtoCDIO(Float cdioNumber, Long raeId){
        RAE rae = raeFinder.findRAEById(raeId);
        CDIO cdio = cdioFinder.findCDIOById(cdioNumber);
        if(rae.getCdioList().contains(cdio))
            throw new AlreadyContains("The rae " + raeId + " from the course " + rae.getCourse().getName() +
                    " already contains the cdio " + cdioNumber);
        rae.addCDIO(cdio);
        cdio.addRAE(rae);
        raeService.saveRAE(rae);
        cdioService.updateCDIO(cdio);
    }

    public void addRAEtoCDIO(Float cdioNumber, Integer courseNumber, String description){
        CDIO cdio = cdioFinder.findCDIOById(cdioNumber);
        RAE rae = raeFinder.findRAEByCourseAndDescription(courseNumber, description);
        if(rae.getCdioList().contains(cdio))
            throw new AlreadyContains("The rae " + rae.getRAEId() + " from the course " +
                    rae.getCourse().getName() + " already contains the cdio " + cdioNumber);
        rae.addCDIO(cdio);
        cdio.addRAE(rae);
        raeService.saveRAE(rae);
        cdioService.updateCDIO(cdio);
    }

    public void deleteRAEFromCDIO(Float cdioNumber, Long raeId){
        RAE rae = raeFinder.findRAEById(raeId);
        CDIO cdio = cdioFinder.findCDIOById(cdioNumber);
        if(!rae.getCdioList().contains(cdio))
            throw new DoesNotContain("The rae " + raeId + " from the course " + rae.getCourse().getName() +
                    " does not contain the cdio " + cdioNumber);
        rae.removeCDIO(cdio);
        cdio.removeRAE(rae);
        raeService.saveRAE(rae);
        cdioService.updateCDIO(cdio);
    }

    public void deleteRAEtFromCDIO(Float cdioNumber, Integer courseNumber, String description){
        CDIO cdio = cdioFinder.findCDIOById(cdioNumber);
        RAE rae = raeFinder.findRAEByCourseAndDescription(courseNumber, description);
        if(!rae.getCdioList().contains(cdio))
            throw new DoesNotContain("The rae " + rae.getRAEId() + " from the course " +
                    rae.getCourse().getName() + " does not contain the cdio " + cdioNumber);
        rae.removeCDIO(cdio);
        cdio.removeRAE(rae);
        raeService.saveRAE(rae);
        cdioService.updateCDIO(cdio);
    }
}
