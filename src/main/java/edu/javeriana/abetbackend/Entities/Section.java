package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSection")
    private Long SectionId;
    @Basic
    private Integer number;
    @Basic
    private String professor;
    @Basic
    private Integer totalStudents;
    @Basic
    private Integer semester;
    @ManyToOne
    @JoinColumn(name = "idCourse")
    private Course course;

    public Section() {
    }

    public Section(Long sectionId, Integer number, String professor, Integer totalStudents, Integer semester, Course course) {
        SectionId = sectionId;
        this.number = number;
        this.professor = professor;
        this.totalStudents = totalStudents;
        this.semester = semester;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getSectionId() {
        return SectionId;
    }

    public void setSectionId(Long SectionId) {
        this.SectionId = SectionId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return number == section.number && totalStudents == section.totalStudents && semester == section.semester && Objects.equal(SectionId, section.SectionId) && Objects.equal(professor, section.professor) && Objects.equal(course, section.course);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(SectionId,professor);
    }
}