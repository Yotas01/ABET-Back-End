package edu.javeriana.abetbackend.Entities;

import com.google.common.base.Objects;
import edu.javeriana.abetbackend.Entities.DTOs.AssessmentToolDTO;
import edu.javeriana.abetbackend.Entities.DTOs.SectionAssessmentToolDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Section_AssessmentTool")
public class SectionAssessmentTool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "section_assessmentToolId")
    private Long sectionAssessmentToolId;
    @ManyToOne
    @JoinColumn(name = "idSection")
    private Section section;
    @ManyToOne
    @JoinColumn(name = "idAssessmentTool", referencedColumnName = "idAssessmentTool")
    private AssessmentTool assessmentTool;
    @Basic
    private Integer totalStudents;
    @Basic
    private Integer semester;
    @Basic
    private int draft;
    @OneToMany(mappedBy = "sectionAssessmentTool")
    private List<SectionPerformanceIndicator> sectionPerformanceIndicators;

    public SectionAssessmentTool() {
        this.sectionPerformanceIndicators = new ArrayList<>();
    }

    public SectionAssessmentTool(SectionAssessmentToolDTO dto){
        if(dto.getId() != null)
            this.sectionAssessmentToolId = dto.getId();
        this.totalStudents = dto.getTotalStudents();
        this.semester = dto.getSemester();
        this.sectionPerformanceIndicators = new ArrayList<>();
        if(dto.isDraft())
            this.draft = 1;
        else
            this.draft = 0;
    }

    public void addSectionPerformanceIndicator(SectionPerformanceIndicator pi){
        this.sectionPerformanceIndicators.add(pi);
    }

    public void removeSectionPerformanceIndicator(SectionPerformanceIndicator pi){
        this.sectionPerformanceIndicators.remove(pi);
    }

    public Long getSectionAssessmentToolId() {
        return sectionAssessmentToolId;
    }

    public void setSectionAssessmentToolId(Long sectionAssessmentToolId) {
        this.sectionAssessmentToolId = sectionAssessmentToolId;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public AssessmentTool getAssessmentTool() {
        return assessmentTool;
    }

    public void setAssessmentTool(AssessmentTool assessmentTool) {
        this.assessmentTool = assessmentTool;
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

    public int getDraft() {
        return draft;
    }

    public void setDraft(int draft) {
        this.draft = draft;
    }

    public List<SectionPerformanceIndicator> getSectionPerformanceIndicators() {
        return sectionPerformanceIndicators;
    }

    public void setSectionPerformanceIndicators(List<SectionPerformanceIndicator> sectionPerformanceIndicators) {
        this.sectionPerformanceIndicators = sectionPerformanceIndicators;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionAssessmentTool that = (SectionAssessmentTool) o;
        return draft == that.draft && Objects.equal(sectionAssessmentToolId, that.sectionAssessmentToolId) && Objects.equal(totalStudents, that.totalStudents) && Objects.equal(semester, that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sectionAssessmentToolId, totalStudents, semester, draft);
    }
}