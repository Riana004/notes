package com.notes.notes.controller;

import com.notes.notes.dto.*;
import com.notes.notes.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotesController {

    private final NotesService notesService;

    /**
     * Endpoint 1: Liste des semestres
     * GET /api/notes/semestres
     */
    @GetMapping("/semestres")
    public ResponseEntity<ApiResponse<List<SemestreDTO>>> getAllSemestres() {
        List<SemestreDTO> semestres = notesService.getAllSemestres();
        return ResponseEntity.ok(ApiResponse.success(semestres));
    }

    /**
     * Endpoint 2: Liste des étudiants avec leurs moyennes S1 à S4
     * GET /api/notes/etudiants/moyennes
     */
    @GetMapping("/etudiants/moyennes")
    public ResponseEntity<ApiResponse<List<EtudiantMoyennesDTO>>> getEtudiantsAvecMoyennes() {
        List<EtudiantMoyennesDTO> etudiants = notesService.getEtudiantsAvecMoyennes();
        return ResponseEntity.ok(ApiResponse.success(etudiants));
    }

    /**
     * Endpoint 3: Relevé de note d'un étudiant pour un semestre donné
     * GET /api/notes/etudiants/{idEtudiant}/releve/{semestre}
     * Paramètre optionnel: parcours (requis pour S4)
     */
    @GetMapping("/etudiants/{idEtudiant}/releve/{semestre}")
    public ResponseEntity<ApiResponse<ReleveNoteDTO>> getReleveNote(
            @PathVariable Long idEtudiant,
            @PathVariable String semestre,
            @RequestParam(required = false) String parcours) {
        ReleveNoteDTO releve = notesService.getReleveNote(idEtudiant, semestre, parcours);
        return ResponseEntity.ok(ApiResponse.success(releve));
    }

    /**
     * Endpoint 4: Info d'un étudiant avec les moyennes S1 à S4
     * GET /api/notes/etudiants/{idEtudiant}/info
     */
    @GetMapping("/etudiants/{idEtudiant}/info")
    public ResponseEntity<ApiResponse<EtudiantInfoDTO>> getEtudiantInfo(@PathVariable Long idEtudiant) {
        EtudiantInfoDTO info = notesService.getEtudiantInfo(idEtudiant);
        return ResponseEntity.ok(ApiResponse.success(info));
    }

    /**
     * Endpoint 5: Notes par semestre pour une année universitaire (L1 ou L2) d'un étudiant
     * GET /api/notes/etudiants/{idEtudiant}/annee/{anneeUniversitaire}
     */
    @GetMapping("/etudiants/{idEtudiant}/annee/{anneeUniversitaire}")
    public ResponseEntity<ApiResponse<List<NotesSemestreAnneeDTO>>> getNotesSemestreParAnnee(
            @PathVariable Long idEtudiant,
            @PathVariable String anneeUniversitaire) {
        List<NotesSemestreAnneeDTO> notes = notesService.getNotesSemestreParAnnee(idEtudiant, anneeUniversitaire);
        return ResponseEntity.ok(ApiResponse.success(notes));
    }
}
