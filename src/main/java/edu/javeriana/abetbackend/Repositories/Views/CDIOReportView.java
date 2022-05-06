package edu.javeriana.abetbackend.Repositories.Views;

import edu.javeriana.abetbackend.Entities.Views.CDIOReport;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CDIOReportView extends CrudRepository<CDIOReport, Float> {
    Optional<CDIOReport> findByCdioNumberAndSemester(Float cdioNumber, Integer semester);
}
