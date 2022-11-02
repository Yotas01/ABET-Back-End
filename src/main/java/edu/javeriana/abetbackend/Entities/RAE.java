package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "RAE")
public class RAE {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id_rae")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id_sae", referencedColumnName = "id_sae")
    private Course course;

    @Column(name = "description")
    private String description;
    @Column(name = "semester")
    private Integer semester;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "RAE_HAS_CDIO",
            joinColumns = @JoinColumn(name = "id_rae"),
            inverseJoinColumns = @JoinColumn(name = "cdio_number")
    )
    private List<CDIO> cdioList;
    @OneToMany(mappedBy = "rae", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PerformanceIndicator> performanceIndicators;

    public RAE(Long RAEId, String description, Integer semester, Course course) {
        this.id = RAEId;
        this.description = description;
        this.course = course;
        this.semester = semester;
        this.performanceIndicators = new ArrayList<>();
        this.cdioList = new ArrayList<>();
    }

    public RAE() {
        this.performanceIndicators = new ArrayList<>();
        this.cdioList = new ArrayList<>();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long RAEId) {
        this.id = RAEId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public List<PerformanceIndicator> getPerformanceIndicators() {
        return performanceIndicators;
    }

    public void setPerformanceIndicators(List<PerformanceIndicator> performanceIndicators) {
        this.performanceIndicators = performanceIndicators;
    }

    public void addPerformanceIndicator(PerformanceIndicator performanceIndicator){
        this.performanceIndicators.add(performanceIndicator);
    }

    public void removePerformanceIndicator(PerformanceIndicator performanceIndicator){
        this.performanceIndicators.remove(performanceIndicator);
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
        cdio.getRaes().add(this);
    }

    public void removeCDIO(CDIO cdio) {
        getCdioList().remove(cdio);
        cdio.getRaes().remove(this);
    }


}