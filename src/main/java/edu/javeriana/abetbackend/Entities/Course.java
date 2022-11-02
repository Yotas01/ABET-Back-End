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
    @Column(name = "id_sae")
    private Integer courseId;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "cdio", cascade = CascadeType.MERGE)
    private List<Course_has_CDIO> cdioList;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Section> sections;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssessmentTool> assessmentTools;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RAE> raeList;


    public Course(Integer courseId, String name) {
        this.courseId = courseId;
        this.name = name;
        this.cdioList = new ArrayList<>();
        this.sections = new ArrayList<>();
        this.assessmentTools = new ArrayList<>();
        this.raeList = new ArrayList<>();
    }

    public Course() {
        this.cdioList = new ArrayList<>();
        this.sections = new ArrayList<>();
        this.assessmentTools = new ArrayList<>();
        this.raeList = new ArrayList<>();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public List<RAE> getRaeList() {
        if (raeList == null) {
            raeList = new ArrayList<>();
        }
        return raeList;
    }

    public void setRaeList(List<RAE> RAEs) {
        this.raeList = RAEs;
    }

    public void addRAE(RAE RAE) {
        getRaeList().add(RAE);
    }

    public void removeRAE(RAE RAE) {
        getRaeList().remove(RAE);
    }

    public List<Course_has_CDIO> getCdioList() {
        return cdioList;
    }

    public void setCdioList(List<Course_has_CDIO> cdioList) {
        this.cdioList = cdioList;
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

    public List<CDIO> getCDIOList(){
        return this.cdioList.stream().map(Course_has_CDIO::getCdio).toList();
    }

    public List<AssessmentTool> getAssessmentTools() {
        return assessmentTools;
    }

    public void setAssessmentTools(List<AssessmentTool> assessmentTools) {
        this.assessmentTools = assessmentTools;
    }

    public void addAssessmentTool(AssessmentTool assessmentTool){
        this.assessmentTools.add(assessmentTool);
    }

    public void removeAssessmentTool(AssessmentTool assessmentTool){
        this.assessmentTools.remove(assessmentTool);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return Objects.equal(courseId, course.courseId) && Objects.equal(name, course.name) && Objects.equal(cdioList, course.cdioList) && Objects.equal(sections, course.sections) && Objects.equal(assessmentTools, course.assessmentTools) && Objects.equal(raeList, course.raeList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(courseId, name, cdioList, sections, assessmentTools, raeList);
    }
}
