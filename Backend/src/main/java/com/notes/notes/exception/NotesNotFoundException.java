package com.notes.notes.exception;

public class NotesNotFoundException extends RuntimeException {
    public NotesNotFoundException(String message) {
        super(message);
    }
    
    public NotesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
