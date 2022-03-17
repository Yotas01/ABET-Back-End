package edu.javeriana.abetbackend.Exceptions.NotFound;

public class AssessmentToolNotFoundById extends RuntimeException {
    public AssessmentToolNotFoundById(String message) {
        super(message);
    }
}
