package com.notes.notes.exception;

import com.notes.notes.dto.ApiResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * Gère les cas où un étudiant n'est pas trouvé
     * Code HTTP: 404
     * Code interne: STUDENT_NOT_FOUND
     */
    @ExceptionHandler(EtudiantNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleEtudiantNotFound(EtudiantNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(
                    "STUDENT_NOT_FOUND", 
                    ex.getMessage(), 
                    null
                ));
    }
    
    /**
     * Gère les cas où les notes ne sont pas trouvées
     * Code HTTP: 404
     * Code interne: NOTES_NOT_FOUND
     */
    @ExceptionHandler(NotesNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotesNotFound(NotesNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(
                    "NOTES_NOT_FOUND", 
                    ex.getMessage(), 
                    null
                ));
    }
    
    /**
     * Gère les erreurs d'authentification
     * Code HTTP: 401
     * Code interne: UNAUTHORIZED
     */
    @ExceptionHandler({UnauthorizedException.class, AuthenticationException.class, BadCredentialsException.class})
    public ResponseEntity<ApiResponse<Object>> handleUnauthorized(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(
                    "UNAUTHORIZED", 
                    "Non authentifié. Veuillez vous connecter.", 
                    ex.getMessage()
                ));
    }
    
    /**
     * Gère les erreurs de base de données
     * Code HTTP: 500
     * Code interne: DB_ERROR
     */
    @ExceptionHandler({DatabaseException.class, DataAccessException.class})
    public ResponseEntity<ApiResponse<Object>> handleDatabaseError(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(
                    "DB_ERROR", 
                    "Erreur lors de l'accès à la base de données.", 
                    ex.getMessage()
                ));
    }
    
    /**
     * Gère toutes les autres erreurs non prévues
     * Code HTTP: 500
     * Code interne: UNKNOWN_ERROR
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleUnknownError(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(
                    "UNKNOWN_ERROR", 
                    "Une erreur inattendue s'est produite.", 
                    ex.getMessage()
                ));
    }
}
