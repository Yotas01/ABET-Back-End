package edu.javeriana.abetbackend.Entities.DTOs;

import edu.javeriana.abetbackend.Entities.SectionComment;

public class SectionCommentDTO {

    private Long commentId;
    private Long sectionId;
    private String comment;

    public SectionCommentDTO(SectionComment comment){
        this.commentId = comment.getId();
        this.sectionId = comment.getSection().getSectionId();
        this.comment = comment.getComment();
    }

    public SectionCommentDTO() {
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
