package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SECTION")
public class Section implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id_section")
    private Long sectionId;

    @ManyToOne
    @JoinColumn(name = "course_id_sae", referencedColumnName = "id_sae")
    private Course course;
    @Column(name = "class_number")
    private Integer classNumber;
    @Column(name = "semester")
    private Integer semester;
    @Column(name = "professor")
    private String professor;
    @Column(name = "total_students")
    private Integer totalStudents;

    @OneToOne(mappedBy = "section")
    private SectionComment comment;

    public Section(Long sectionId, Course course, Integer classNumber, Integer semester, String professor, Integer totalStudents) {
        this.sectionId = sectionId;
        this.course = course;
        this.classNumber = classNumber;
        this.semester = semester;
        this.professor = professor;
        this.totalStudents = totalStudents;
    }

    public Section() {
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public SectionComment getComment() {
        return comment;
    }

    public void setComment(SectionComment comment) {
        this.comment = comment;
    }
}