package edu.javeriana.abetbackend.Entities;

import javax.persistence.*;

@Entity
@Table(name = "SECTION_COMMENT")
public class SectionComment {

    @Id
    @Column(name = "id_comment")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_section", referencedColumnName = "id_section")
    private Section section;

    @Column(name = "comment")
    private String comment;

    public SectionComment(Long id, Section section, String comment) {
        this.id = id;
        this.section = section;
        this.comment = comment;
    }

    public SectionComment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
