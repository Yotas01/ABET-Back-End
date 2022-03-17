package edu.javeriana.abetbackend.Exceptions.AlreadyExists;

public class CourseAlreadyExists extends RuntimeException {
    public CourseAlreadyExists(String message) {
        super(message);
    }
}
