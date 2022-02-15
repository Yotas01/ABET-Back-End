package edu.javeriana.abetbackend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class CDIO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCDIO")
    private Long CDIOId;
    @Basic
    private String description;
    @Basic
    private Float number;
    @ManyToMany(mappedBy = "cdioList", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Outcome> outcomes;
    @ManyToMany(mappedBy = "cdioList")
    @JsonIgnore
    private List<RAE> RAEs;
    @ManyToMany(mappedBy = "cdioList")
    private List<Course> courses;

    public CDIO(Long CDIOId, String description, Float number) {
        this.CDIOId = CDIOId;
        this.description = description;
        this.number = number;
        this.courses = new ArrayList<>();
        this.outcomes = new ArrayList<>();
        this.RAEs = new ArrayList<>();
    }

    public CDIO() {
        this.courses = new ArrayList<>();
        this.outcomes = new ArrayList<>();
        this.RAEs = new ArrayList<>();
    }

    public Long getCDIOId() {
        return CDIOId;
    }

    public void setCDIOId(Long CDIOId) {
        this.CDIOId = CDIOId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }

    public List<Outcome> getOutcomes() {
        if (outcomes == null) {
            outcomes = new ArrayList<>();
        }
        return outcomes;
    }

    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    public void addOutcome(Outcome outcome) {
        getOutcomes().add(outcome);
    }

    public void removeOutcome(Outcome outcome) {
        getOutcomes().remove(outcome);
    }

    public List<RAE> getRAEs() {
        if (RAEs == null) {
            RAEs = new ArrayList<>();
        }
        return RAEs;
    }

    public void setRAEs(List<RAE> RAEs) {
        this.RAEs = RAEs;
    }

    public void addRAE(RAE RAE) {
        getRAEs().add(RAE);
    }

    public void removeRAE(RAE RAE) {
        getRAEs().remove(RAE);
    }

    public List<Course> getCourses() {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        getCourses().add(course);
    }

    public void removeCourse(Course course) {
        getCourses().remove(course);
    }

    @Override
    public String toString() {
        return "CDIO{" +
                "CDIOId=" + CDIOId +
                ", description='" + description + '\'' +
                ", number=" + number +
                ", outcomes=" + outcomes +
                ", RAEs=" + RAEs +
                ", courses=" + courses +
                '}';
    }
}
