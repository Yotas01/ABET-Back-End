package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import edu.javeriana.abetbackend.Entities.DTOs.CDIODTO;
import edu.javeriana.abetbackend.Entities.DTOs.RAEDTO;
import edu.javeriana.abetbackend.Entities.DTOs.SectionDTO;

import java.util.ArrayList;
import java.util.List;

public class CourseReview {

    private Long CourseId;
    private Integer number;
    private String name;
    private SectionDTO section;
    private List<RAEDTO> RAEs;
    private List<CDIODTO> cdioList;

    public CourseReview(Course course, Section section) {
        CourseId = course.getCourseId();
        this.number = course.getNumber();
        this.name = course.getName();
        this.section = new SectionDTO(section);
        this.cdioList = new ArrayList<>();
        this.RAEs = new ArrayList<>();
        course.getRAEs().forEach(rae -> this.RAEs.add(new RAEDTO(rae)));
        course.getCdioList().forEach(cdio -> this.cdioList.add(new CDIODTO(cdio)));
    }

    public CourseReview() {
        this.cdioList = new ArrayList<>();
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

    public List<CDIODTO> getCdioList() {
        return this.cdioList;
    }

    public void setCdioList(List<CDIODTO> CDIos) {
        this.cdioList = CDIos;
    }

    public void addCDIo(CDIODTO CDIo) {
        getCdioList().add(CDIo);
    }

    public void removeCDIo(CDIODTO CDIo) {
        getCdioList().remove(CDIo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseReview course = (CourseReview) o;
        return Objects.equal(CourseId, course.CourseId) && Objects.equal(number, course.number) && Objects.equal(name, course.name) && Objects.equal(section, course.section) && Objects.equal(RAEs, course.RAEs) && Objects.equal(cdioList, course.cdioList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(CourseId, number, name);
    }

    @Override
    public String toString() {
        return "Course Report{" +
                "CourseId=" + CourseId +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", section'"+ section + '\''+
                '}';
    }
}
