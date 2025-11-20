package com.notes.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private String codeMatiere;
    private String intituleMatiere;
    private Integer credit;
    private BigDecimal valeur;
    private String semestre;
}
