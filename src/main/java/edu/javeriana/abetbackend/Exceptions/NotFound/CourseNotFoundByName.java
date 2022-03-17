package edu.javeriana.abetbackend.Exceptions.NotFound;

public class CourseNotFoundByName extends RuntimeException {
    public CourseNotFoundByName(String message) {
        super(message);
    }
}
