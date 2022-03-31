package edu.javeriana.abetbackend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class CDIO {

    @Id
    @Column(name = "number")
    private Float number;
    @Basic
    private String description;
    @ManyToMany(mappedBy = "cdioList", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Outcome> outcomes;
    @ManyToMany(mappedBy = "cdioList", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RAE> RAEs;
    @ManyToMany(mappedBy = "cdioList", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Course> courses;

    public CDIO(String description, Float number) {
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
                ", description='" + description + '\'' +
                ", number=" + number +
                ", outcomes=" + outcomes +
                ", RAEs=" + RAEs +
                ", courses=" + courses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CDIO cdio = (CDIO) o;
        return Objects.equal(description, cdio.description) && Objects.equal(number, cdio.number) && Objects.equal(outcomes, cdio.outcomes) && Objects.equal(RAEs, cdio.RAEs) && Objects.equal(courses, cdio.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(description, number);
    }
}
