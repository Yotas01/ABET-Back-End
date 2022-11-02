package edu.javeriana.abetbackend.Entities;

import edu.javeriana.abetbackend.Entities.Ids.SectionId;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "SECTION_COMMENT")
public class SectionComment {

    @Id
    @Column(name = "id_comment")
    private Long id;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "class_number", referencedColumnName = "class_number"),
            @JoinColumn(name = "semester", referencedColumnName = "semester")
    })
    private Section section;

    @Column(name = "comment")
    private String comment;

    public SectionComment(Section section, String comment) {
        this.section = section;
        this.comment = comment;
    }

    public SectionComment() {
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
