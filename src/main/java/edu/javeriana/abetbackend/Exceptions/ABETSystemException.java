package edu.javeriana.abetbackend.Exceptions;

public class ABETSystemException{
    public String issue;
    public Integer status;

    public ABETSystemException(String issue, Integer status) {
        this.issue = issue;
        this.status = status;
    }
}
