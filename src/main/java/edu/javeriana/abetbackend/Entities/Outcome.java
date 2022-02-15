package edu.javeriana.abetbackend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idOutcome")
    private Long OutcomeId;
    @Basic
    private String description;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "outcome_has_cdio",
            joinColumns = @JoinColumn(name = "idOutcome"),
            inverseJoinColumns = @JoinColumn(name = "idCDIO")
    )
    @JsonIgnore
    private List<CDIO> cdioList;

    public Outcome(Long outcomeId, String description) {
        OutcomeId = outcomeId;
        this.description = description;
        this.cdioList = new ArrayList<>();
    }

    public Outcome() {
        this.cdioList = new ArrayList<>();
    }

    public Long getOutcomeId() {
        return OutcomeId;
    }

    public void setOutcomeId(Long OutcomeId) {
        this.OutcomeId = OutcomeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CDIO> getCDIos() {
        if (cdioList == null) {
            cdioList = new ArrayList<>();
        }
        return cdioList;
    }

    public void setCDIos(List<CDIO> CDIos) {
        this.cdioList = CDIos;
    }

    public void addCDIo(CDIO CDIo) {
        getCDIos().add(CDIo);
    }

    public void removeCDIo(CDIO CDIo) {
        getCDIos().remove(CDIo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outcome outcome = (Outcome) o;
        return Objects.equal(OutcomeId, outcome.OutcomeId) && Objects.equal(description, outcome.description) && Objects.equal(cdioList, outcome.cdioList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(OutcomeId, description, cdioList);
    }

    @Override
    public String toString() {
        return "Outcome{" +
                "OutcomeId=" + OutcomeId +
                ", description='" + description + '\'' +
                ", cdioList=" + cdioList +
                '}';
    }
}
