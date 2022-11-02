package edu.javeriana.abetbackend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OUTCOME")
public class Outcome {

    @Id
    @Column(name = "id_outcome")
    private Integer outcomeId;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "CDIO_HAS_OUTCOME",
            joinColumns = @JoinColumn(name = "id_outcome"),
            inverseJoinColumns = @JoinColumn(name = "cdio_number")
    )
    @JsonIgnore
    private List<CDIO> cdioList;

    @OneToMany(mappedBy = "outcome")
    private List<SemesterReport> semesterReports;

    public Outcome(Integer outcomeId, String description) {
        this.outcomeId = outcomeId;
        this.description = description;
        this.cdioList = new ArrayList<>();
    }

    public Outcome() {
        this.cdioList = new ArrayList<>();
    }

    public Integer getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Integer OutcomeId) {
        this.outcomeId = OutcomeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CDIO> getCdioList() {
        return cdioList;
    }

    public void setCdioList(List<CDIO> cdioList) {
        this.cdioList = cdioList;
    }

    public void addCdio(CDIO cdio){
        this.cdioList.add(cdio);
    }

    public void removeCdio(CDIO cdio){
        this.cdioList.remove(cdio);
    }

    public List<SemesterReport> getSemesterReports() {
        return semesterReports;
    }

    public void setSemesterReports(List<SemesterReport> semesterReports) {
        this.semesterReports = semesterReports;
    }

    public void addSemesterReport(SemesterReport semesterReport){
        this.semesterReports.add(semesterReport);
    }

    public void removeSemesterReport(SemesterReport semesterReport){
        this.semesterReports.remove(semesterReport);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Outcome outcome)) return false;
        return Objects.equal(outcomeId, outcome.outcomeId) && Objects.equal(description, outcome.description) && Objects.equal(cdioList, outcome.cdioList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(outcomeId, description, cdioList);
    }
}
