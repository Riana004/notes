package com.notes.notes.controller;

import com.notes.notes.dto.*;
import com.notes.notes.service.NotesServiceNew;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotesControllerNew {

    private final NotesServiceNew notesService;

    /**
     * Endpoint 1: Liste des semestres
     * GET /api/notes/semestres
     */
    @GetMapping("/semestres")
    public ResponseEntity<List<SemestreDTO>> getAllSemestres() {
        return ResponseEntity.ok(notesService.getAllSemestres());
    }

    /**
     * Endpoint 2: Liste des étudiants avec leurs moyennes S1 à S4
     * GET /api/notes/etudiants/moyennes
     */
    @GetMapping("/etudiants/moyennes")
    public ResponseEntity<List<EtudiantMoyennesDTO>> getEtudiantsAvecMoyennes() {
        return ResponseEntity.ok(notesService.getEtudiantsAvecMoyennes());
    }

    /**
     * Endpoint 3: Relevé de note d'un étudiant pour un semestre donné
     * GET /api/notes/etudiants/{idEtudiant}/releve/{semestre}
     * Paramètre optionnel: parcours (requis pour S4)
     */
    @GetMapping("/etudiants/{idEtudiant}/releve/{semestre}")
    public ResponseEntity<ReleveNoteDTO> getReleveNote(
            @PathVariable Long idEtudiant,
            @PathVariable String semestre,
            @RequestParam(required = false) String parcours) {
        return ResponseEntity.ok(notesService.getReleveNote(idEtudiant, semestre, parcours));
    }

    /**
     * Endpoint 4: Info d'un étudiant avec les moyennes S1 à S4
     * GET /api/notes/etudiants/{idEtudiant}/info
     */
    @GetMapping("/etudiants/{idEtudiant}/info")
    public ResponseEntity<EtudiantInfoDTO> getEtudiantInfo(@PathVariable Long idEtudiant) {
        return ResponseEntity.ok(notesService.getEtudiantInfo(idEtudiant));
    }

    /**
     * Endpoint 5: Notes par semestre pour une année universitaire (L1 ou L2) d'un étudiant
     * GET /api/notes/etudiants/{idEtudiant}/annee/{anneeUniversitaire}
     */
    @GetMapping("/etudiants/{idEtudiant}/annee/{anneeUniversitaire}")
    public ResponseEntity<List<NotesSemestreAnneeDTO>> getNotesSemestreParAnnee(
            @PathVariable Long idEtudiant,
            @PathVariable String anneeUniversitaire) {
        return ResponseEntity.ok(notesService.getNotesSemestreParAnnee(idEtudiant, anneeUniversitaire));
    }
}
