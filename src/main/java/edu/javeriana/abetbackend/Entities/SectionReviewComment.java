package edu.javeriana.abetbackend.Entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "SECTION_REVIEW_COMMENT")
public class SectionReviewComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id_section_review_comment")
    private Long sectionReviewCommentId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_section", referencedColumnName = "id_section")
    private Section section;
    @Basic
    @Column(name = "semester")
    private Integer semester;
    @Basic
    @Column(name = "comment")
    private String comment;

    public SectionReviewComment(Section section, Integer semester, String comment) {
        this.section = section;
        this.semester = semester;
        this.comment = comment;
    }

    public SectionReviewComment() {
    }

    public Long getSectionReviewCommentId() {
        return sectionReviewCommentId;
    }

    public void setSectionReviewCommentId(Long sectionReviewCommentId) {
        this.sectionReviewCommentId = sectionReviewCommentId;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
