package edu.javeriana.abetbackend.Repositories.Views;

import edu.javeriana.abetbackend.Entities.Views.CDIOReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.Optional;

@Repository
public interface CDIOReportView extends CrudRepository<CDIOReport, Float> {
    Optional<CDIOReport> findByCdioNumberAndSemester(Float cdioNumber, Integer semester);
}
