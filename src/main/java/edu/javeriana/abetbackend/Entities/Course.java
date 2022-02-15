package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCourse")
    private Long CourseId;
    @Basic
    private Integer number;
    @Basic
    private String name;
    @OneToMany(mappedBy = "course")
    private List<Section> sections;
    @OneToMany(mappedBy = "course")
    private List<RAE> RAEs;
    @ManyToMany
    @JoinTable(
            name = "course_has_cdio",
            joinColumns = @JoinColumn(name = "idCourse"),
            inverseJoinColumns = @JoinColumn(name = "idCDIO")
    )
    private List<CDIO> cdioList;

    public Course(Long courseId, Integer number, String name) {
        CourseId = courseId;
        this.number = number;
        this.name = name;
        this.cdioList = new ArrayList<>();
        this.sections = new ArrayList<>();
        this.RAEs = new ArrayList<>();
    }

    public Course() {
        this.cdioList = new ArrayList<>();
        this.sections = new ArrayList<>();
        this.RAEs = new ArrayList<>();
    }

    public Long getCourseId() {
        return CourseId;
    }

    public void setCourseId(Long CourseId) {
        this.CourseId = CourseId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Section> getSections() {
        if (sections == null) {
            sections = new ArrayList<>();
        }
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        getSections().add(section);
    }

    public void removeSection(Section section) {
        getSections().remove(section);
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

    public List<CDIO> getCdioList() {
        if (cdioList == null) {
            cdioList = new ArrayList<>();
        }
        return cdioList;
    }

    public void setCdioList(List<CDIO> CDIos) {
        this.cdioList = CDIos;
    }

    public void addCDIo(CDIO CDIo) {
        getCdioList().add(CDIo);
        CDIo.getCourses().add(this);
    }

    public void removeCDIo(CDIO CDIo) {
        getCdioList().remove(CDIo);
        CDIo.getCourses().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equal(CourseId, course.CourseId) && Objects.equal(number, course.number) && Objects.equal(name, course.name) && Objects.equal(sections, course.sections) && Objects.equal(RAEs, course.RAEs) && Objects.equal(cdioList, course.cdioList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(CourseId, number, name, sections, RAEs, cdioList);
    }
}
