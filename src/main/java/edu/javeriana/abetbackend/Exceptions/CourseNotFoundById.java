package edu.javeriana.abetbackend.Exceptions;

public class CourseNotFoundById extends RuntimeException {
    public CourseNotFoundById(String message) {
        super(message);
    }
}
