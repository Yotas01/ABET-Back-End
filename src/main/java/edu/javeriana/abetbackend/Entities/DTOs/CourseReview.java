package edu.javeriana.abetbackend.Entities.DTOs;

import com.google.common.base.Objects;
import edu.javeriana.abetbackend.Entities.Course;
import edu.javeriana.abetbackend.Entities.Section;

import java.util.ArrayList;
import java.util.List;

public class CourseReview {

    private Long courseId;
    private Integer number;
    private String name;
    private SectionDTO section;
    private List<Float> cdioList;
    private List<RAEDTO> RAEs;
    private SectionReview sectionReview;

    public CourseReview(Course course, Section section) {
        this.courseId = course.getCourseId();
        this.number = course.getNumber();
        this.name = course.getName();
        this.section = new SectionDTO(section);
        this.cdioList = new ArrayList<>();
        this.RAEs = new ArrayList<>();
        course.getRAEs().forEach(rae -> this.RAEs.add(new RAEDTO(rae)));
        course.getCdioList().forEach(cdio -> this.cdioList.add(cdio.getNumber()));
        this.sectionReview = new SectionReview();
    }

    public CourseReview() {
        this.cdioList = new ArrayList<>();
        this.RAEs = new ArrayList<>();
        this.sectionReview = new SectionReview();
    }

    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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

    public SectionDTO getSection() {
        return this.section;
    }

    public void setSection(SectionDTO section) {
        this.section = section;
    }

    public List<RAEDTO> getRAEs() {
        return RAEs;
    }

    public void setRAEs(List<RAEDTO> RAEs) {
        this.RAEs = RAEs;
    }

    public void addRAE(RAEDTO RAE) {
        getRAEs().add(RAE);
    }

    public void removeRAE(RAEDTO RAE) {
        getRAEs().remove(RAE);
    }

    public List<Float> getCdioList() {
        return this.cdioList;
    }

    public void setCdioList(List<Float> CDIos) {
        this.cdioList = CDIos;
    }

    public void addCDIo(CDIODTO CDIo) {
        getCdioList().add(CDIo.getNumber());
    }

    public void removeCDIo(CDIODTO CDIo) {
        getCdioList().remove(CDIo.getNumber());
    }

    public SectionReview getSectionReview() {
        return sectionReview;
    }

    public void setSectionReview(SectionReview sectionReview) {
        this.sectionReview = sectionReview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseReview course = (CourseReview) o;
        return Objects.equal(this.courseId, course.courseId) && Objects.equal(number, course.number) && Objects.equal(name, course.name) && Objects.equal(section, course.section) && Objects.equal(RAEs, course.RAEs) && Objects.equal(cdioList, course.cdioList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(courseId, number, name);
    }

    @Override
    public String toString() {
        return "Course Report{" +
                "CourseId=" + courseId +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", section'"+ section + '\''+
                '}';
    }
}
