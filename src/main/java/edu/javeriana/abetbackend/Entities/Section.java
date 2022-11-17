package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SECTION")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_section")
    private Long SectionId;
    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;
    @Basic
    private Integer semester;
    @Basic
    private Integer number;
    @Basic
    private String professor;
    @Basic
    @Column(name = "total_students")
    private Integer totalStudents;
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<SectionAssessmentTool> sectionAssessmentTool;
    @OneToOne(mappedBy = "section", cascade = CascadeType.ALL)
    private SectionReviewComment comment;

    public Section() {
        this.sectionAssessmentTool = new ArrayList<>();
    }

    public Section(Long sectionId, Integer number, String professor, Integer totalStudents, Integer semester, Course course, SectionReviewComment comment) {
        SectionId = sectionId;
        this.number = number;
        this.professor = professor;
        this.totalStudents = totalStudents;
        this.semester = semester;
        this.course = course;
        this.comment = comment;
        this.sectionAssessmentTool = new ArrayList<>();
    }

    public void addSectionAssessmentTool(SectionAssessmentTool sectionAssessmentTool){
        this.sectionAssessmentTool.add(sectionAssessmentTool);
    }

    public void removeSectionAssessmentTool(SectionAssessmentTool sectionAssessmentTool){
        this.sectionAssessmentTool.remove(sectionAssessmentTool);
    }

    public List<SectionAssessmentTool> getSectionAssessmentTool() {
        return sectionAssessmentTool;
    }

    public void setSectionAssessmentTool(List<SectionAssessmentTool> sectionAssessmentTool) {
        this.sectionAssessmentTool = sectionAssessmentTool;
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

    public SectionReviewComment getComment() {
        return comment;
    }

    public void setComment(SectionReviewComment comment) {
        this.comment = comment;
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