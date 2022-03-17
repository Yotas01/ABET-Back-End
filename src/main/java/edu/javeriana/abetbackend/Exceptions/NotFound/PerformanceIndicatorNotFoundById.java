package edu.javeriana.abetbackend.Exceptions.NotFound;

public class PerformanceIndicatorNotFoundById extends RuntimeException {
    public PerformanceIndicatorNotFoundById(String message) {
        super(message);
    }
}
