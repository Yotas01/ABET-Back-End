package edu.javeriana.abetbackend.Exceptions.NotFound;

public class CourseNotFoundById extends RuntimeException {
    public CourseNotFoundById(String message) {
        super(message);
    }
}
