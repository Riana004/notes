package com.notes.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReleveNoteDTO {
    private Long idEtudiant;
    private String matricule;
    private String nom;
    private String prenom;
    private String semestre;
    private String parcours;
    private List<NoteDetailDTO> notes;
    private BigDecimal moyenne;
    private Integer totalCredits;
}
