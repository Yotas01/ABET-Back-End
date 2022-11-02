package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import edu.javeriana.abetbackend.Entities.Ids.SectionId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SECTION")
@IdClass(SectionId.class)
public class Section implements Serializable {

    @Id
    @Column(name = "class_number")
    public Integer classNumber;
    @Id
    @Column(name = "semester")
    public Integer semester;

    @ManyToOne
    @JoinColumn(name = "course_id_sae", referencedColumnName = "id_sae")
    private Course course;
    @Column(name = "professor")
    private String professor;
    @Column(name = "total_students")
    private Integer totalStudents;

    @OneToMany(mappedBy = "section")
    private List<SectionReport> sectionReports;
    @OneToOne(mappedBy = "section")
    private SectionComment comment;

    public Section(Integer classNumber, Integer semester, Course course, String professor, Integer totalStudents) {
        this.classNumber = classNumber;
        this.semester = semester;
        this.course = course;
        this.professor = professor;
        this.totalStudents = totalStudents;
    }

    public Section() {
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public List<SectionReport> getSectionReports() {
        return sectionReports;
    }

    public void setSectionReports(List<SectionReport> sectionReports) {
        this.sectionReports = sectionReports;
    }

    public void addSectionReport(SectionReport sectionReport){
        this.sectionReports.add(sectionReport);
    }

    public void removeSectionReport(SectionReport sectionReport){
        this.sectionReports.remove(sectionReport);
    }

    public SectionComment getComment() {
        return comment;
    }

    public void setComment(SectionComment comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section section)) return false;
        return Objects.equal(classNumber, section.classNumber) && Objects.equal(semester, section.semester) && Objects.equal(course, section.course) && Objects.equal(professor, section.professor) && Objects.equal(totalStudents, section.totalStudents) && Objects.equal(sectionReports, section.sectionReports) && Objects.equal(comment, section.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(classNumber, semester, course, professor, totalStudents, sectionReports, comment);
    }
}