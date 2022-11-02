package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.SectionComment;

public class SectionCommentDTO {

    private Integer classNumber;
    private String comment;

    public SectionCommentDTO(SectionComment comment){
        this.classNumber = comment.getSection().getClassNumber();
        this.comment = comment.getComment();
    }

    public SectionCommentDTO() {
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
