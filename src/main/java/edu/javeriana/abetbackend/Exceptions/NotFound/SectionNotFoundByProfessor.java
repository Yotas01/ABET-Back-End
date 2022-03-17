package edu.javeriana.abetbackend.Exceptions.NotFound;

public class SectionNotFoundByProfessor extends RuntimeException {
    public SectionNotFoundByProfessor(String message) {
        super(message);
    }
}
