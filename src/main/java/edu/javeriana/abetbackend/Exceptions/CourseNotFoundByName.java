package edu.javeriana.abetbackend.Exceptions;

public class CourseNotFoundByName extends RuntimeException {
    public CourseNotFoundByName(String message) {
        super(message);
    }
}
