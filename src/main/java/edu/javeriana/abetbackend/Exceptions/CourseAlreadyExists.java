package edu.javeriana.abetbackend.Exceptions;

public class CourseAlreadyExists extends RuntimeException {
    public CourseAlreadyExists(String message) {
        super(message);
    }
}
