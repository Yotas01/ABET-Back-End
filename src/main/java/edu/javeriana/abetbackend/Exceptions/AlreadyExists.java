package edu.javeriana.abetbackend.Exceptions;

public class AlreadyExists extends RuntimeException{
    public AlreadyExists(String message) {
        super(message);
    }
}
