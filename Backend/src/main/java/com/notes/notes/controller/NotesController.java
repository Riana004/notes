package com.notes.notes.controller;

import com.notes.notes.dto.ApiResponse;
import com.notes.notes.dto.NotesResponse;
import com.notes.notes.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NotesController {
    
    private final NotesService notesService;
    
    /**
     * Récupère les notes d'un étudiant pour un semestre donné
     * @param etu Numéro ETU de l'étudiant
     * @param semestre Nom du semestre (ex: "Semestre 1", "Semestre 2", etc.)
     * @return ApiResponse contenant les notes, la moyenne et les crédits
     * 
     * Exemple: GET /api/notes/etudiant/ETU001/semestre/Semestre 1
     */
    @GetMapping("/etudiants/{etu}/semestres/{semestre}")
    public ResponseEntity<ApiResponse<NotesResponse>> getNotesBySemestre(
            @PathVariable String etu,
            @PathVariable String semestre) {
        
        // Remplacer les underscores par des espaces si nécessaire
        String semestreNom = semestre.replace("_", " ");
        NotesResponse response = notesService.getNotesBySemestre(etu, semestreNom);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    /**
     * Récupère les notes d'un étudiant pour une année d'étude donnée
     * @param etu Numéro ETU de l'étudiant
     * @param annee Nom de l'année (ex: "Licence 1", "Licence 2", "Master 1", etc.)
     * @return ApiResponse contenant les notes, la moyenne et les crédits
     * 
     * Exemple: GET /api/notes/etudiant/ETU001/annee/Licence 1
     */
    @GetMapping("/etudiants/{etu}/annees/{annee}")
    public ResponseEntity<ApiResponse<NotesResponse>> getNotesByAnnee(
            @PathVariable String etu,
            @PathVariable String annee) {
        
        // Remplacer les underscores par des espaces si nécessaire
        String anneeNom = annee.replace("_", " ");
        NotesResponse response = notesService.getNotesByAnnee(etu, anneeNom);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
