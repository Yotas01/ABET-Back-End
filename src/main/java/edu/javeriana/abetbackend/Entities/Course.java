package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import edu.javeriana.abetbackend.Entities.Ids.Course_has_CdioId;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private Long CourseId;
    @Basic
    private Integer number;
    @Basic
    private String name;
    @Basic
    @Column(name = "id_sae")
    private String idSAE;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Section> sections;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RAE> RAEs;
    @OneToMany(mappedBy = "cdio", cascade = CascadeType.MERGE)
    private List<Course_has_CDIO> cdioList;

    public Course(Long courseId, Integer number, String name, String idSAE) {
        CourseId = courseId;
        this.number = number;
        this.name = name;
        this.idSAE = idSAE;
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

    public String getIdSAE() {
        return idSAE;
    }

    public void setIdSAE(String idSAE) {
        this.idSAE = idSAE;
    }

    public List<Course_has_CDIO> getCdioList() {
        return cdioList;
    }

    public void setCdioList(List<Course_has_CDIO> cdioList) {
        this.cdioList = cdioList;
    }

    public List<CDIO> getListOfCDIO() {
        if (cdioList == null) {
            cdioList = new ArrayList<>();
        }
        return this.cdioList.stream().map(Course_has_CDIO::getCdio).toList();
    }

    public void addCDIO(CDIO cdio, Integer bloomValue) {
        Course_has_CDIO course_has_cdio = new Course_has_CDIO(new Course_has_CdioId(this.getCourseId(),cdio.getNumber()),this, cdio, bloomValue);
        this.cdioList.add(course_has_cdio);
        cdio.addCourse(this,bloomValue);
    }

    public void removeCDIO(CDIO cdio) {
        Course_has_CDIO course_has_cdio = new Course_has_CDIO(new Course_has_CdioId(this.getCourseId(),cdio.getNumber()),this, cdio, null);
        this.cdioList.remove(course_has_cdio);
        cdio.removeCourse(this);
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
        return Objects.hashCode(CourseId, number, name);
    }

    @Override
    public String toString() {
        return "Course{" +
                "CourseId=" + CourseId +
                ", number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
