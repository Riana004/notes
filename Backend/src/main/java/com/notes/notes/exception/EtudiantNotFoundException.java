package com.notes.notes.exception;

public class EtudiantNotFoundException extends RuntimeException {
    public EtudiantNotFoundException(String message) {
        super(message);
    }
    
    public EtudiantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
