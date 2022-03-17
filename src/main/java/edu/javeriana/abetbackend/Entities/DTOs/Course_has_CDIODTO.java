package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.Course_has_CDIO;

public class Course_has_CDIODTO {

    private String course;
    private Float cdio;
    private Integer value;

    public Course_has_CDIODTO(Course_has_CDIO course_has_cdio) {
        this.course = course_has_cdio.getId().getCourse().getName();
        this.cdio = course_has_cdio.getId().getCdio().getNumber();
        this.value = course_has_cdio.getValue();
    }

    public Course_has_CDIODTO() {
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Float getCdio() {
        return cdio;
    }

    public void setCdio(Float cdio) {
        this.cdio = cdio;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
