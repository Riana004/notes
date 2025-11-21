package com.notes.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotesSemestreAnneeDTO {
    private Long idEtudiant;
    private String matricule;
    private String nom;
    private String prenom;
    private String anneeUniversitaire;
    private String semestre;
    private Map<String, List<NoteDetailDTO>> notesParParcours; // Pour S4: parcours -> notes, pour autres: clé unique -> notes
}
