package edu.javeriana.abetbackend.Exceptions;

public class SectionAlreadyExists extends RuntimeException {
    public SectionAlreadyExists(String message) {
        super(message);
    }
}
