package edu.javeriana.abetbackend.Exceptions.DoesNotContain;

public class CourseDoesNotContainSection extends RuntimeException {
    public CourseDoesNotContainSection(String message) {
        super(message);
    }
}
