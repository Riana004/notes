package com.notes.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotesResponse {
    private String etudiant;
    private String etu;
    private String periode; // "Semestre 1" ou "Licence 1"
    private List<NoteDTO> notes;
    private BigDecimal moyenne;
    private Integer totalCredits;
}
