package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class RAE {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "idRAE")
    private Long RAEId;
    @Basic
    private String description;
    @ManyToOne
    @JoinColumn(name = "idCourse")
    private Course course;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "rae_has_cdio",
            joinColumns = @JoinColumn(name = "idRAE"),
            inverseJoinColumns = @JoinColumn(name = "cdioNumber")
    )
    private List<CDIO> cdioList;
    @OneToMany(mappedBy = "rae", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AssessmentTool> assessmentTools;

    public RAE(Long RAEId, String description, Course course) {
        this.RAEId = RAEId;
        this.description = description;
        this.course = course;
        this.assessmentTools = new ArrayList<>();
        this.cdioList = new ArrayList<>();
    }

    public RAE() {
        this.assessmentTools = new ArrayList<>();
        this.cdioList = new ArrayList<>();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getRAEId() {
        return RAEId;
    }

    public void setRAEId(Long RAEId) {
        this.RAEId = RAEId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AssessmentTool> getAssessmentTools() {
        if (assessmentTools == null) {
            assessmentTools = new ArrayList<>();
        }
        return assessmentTools;
    }

    public void setAssessmentTools(List<AssessmentTool> assessmentTools) {
        this.assessmentTools = assessmentTools;
    }

    public void addAssessmentTool(AssessmentTool assessmentTool) {
        getAssessmentTools().add(assessmentTool);
    }

    public void removeAssessmentTool(AssessmentTool assessmentTool) {
        getAssessmentTools().remove(assessmentTool);
    }

    public List<CDIO> getCdioList() {
        if (cdioList == null) {
            cdioList = new ArrayList<>();
        }
        return cdioList;
    }

    public void setCdioList(List<CDIO> CDIos) {
        this.cdioList = CDIos;
    }

    public void addCDIO(CDIO cdio) {
        getCdioList().add(cdio);
        cdio.getRAEs().add(this);
    }

    public void removeCDIO(CDIO cdio) {
        getCdioList().remove(cdio);
        cdio.getRAEs().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RAE rae = (RAE) o;
        return Objects.equal(RAEId, rae.RAEId) && Objects.equal(description, rae.description) && Objects.equal(course, rae.course) && Objects.equal(assessmentTools, rae.assessmentTools) && Objects.equal(cdioList, rae.cdioList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(RAEId, description);
    }

    @Override
    public String toString() {
        return "RAEId=" + RAEId +
                ", description='" + description + '\'' +
                ", course=" + course.getNumber() + "-" + course.getName();
    }
}