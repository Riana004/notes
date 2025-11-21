package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "etudiant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etudiant")
    private Long idEtudiant;
    
    @Column(nullable = false, unique = true, length = 50)
    private String matricule;
    
    @Column(nullable = false, length = 50)
    private String nom;
    
    @Column(nullable = false, length = 50)
    private String prenom;
    
    @OneToMany(mappedBy = "etudiant")
    private List<InscriptionSemestre> inscriptions;
    
    @OneToMany(mappedBy = "etudiant")
    private List<Examen> examens;
}

