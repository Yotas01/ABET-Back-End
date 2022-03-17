package edu.javeriana.abetbackend.Exceptions.NotFound;

public class OutcomeNotFoundById extends RuntimeException {
    public OutcomeNotFoundById(String message) {
        super(message);
    }
}
