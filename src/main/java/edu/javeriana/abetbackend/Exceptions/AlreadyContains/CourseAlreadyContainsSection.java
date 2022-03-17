package edu.javeriana.abetbackend.Exceptions.AlreadyContains;

public class CourseAlreadyContainsSection extends RuntimeException {
    public CourseAlreadyContainsSection(String message) {
        super(message);
    }
}
