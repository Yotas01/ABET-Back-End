package edu.javeriana.abetbackend.Exceptions;

public class OutcomeNotFoundById extends RuntimeException {
    public OutcomeNotFoundById(String message) {
        super(message);
    }
}
