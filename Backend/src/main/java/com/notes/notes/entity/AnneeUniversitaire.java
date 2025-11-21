package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "anneeuniversitaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnneeUniversitaire {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_annee_universitaire")
    private Long idAnneeUniversitaire;
    
    @Column(nullable = false, length = 50)
    private String intitule;
    
    @OneToMany(mappedBy = "anneeUniversitaire")
    private List<AnneeSemestre> anneeSemestres;
}

