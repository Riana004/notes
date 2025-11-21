package com.notes.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantInfoDTO {
    private Long idEtudiant;
    private String matricule;
    private String nom;
    private String prenom;
    private BigDecimal moyenneS1;
    private BigDecimal moyenneS2;
    private BigDecimal moyenneS3;
    private Map<String, BigDecimal> moyennesS4; // Map parcours -> moyenne
}
