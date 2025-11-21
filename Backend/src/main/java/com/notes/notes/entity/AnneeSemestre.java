package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "anneesemestre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnneeSemestre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_annee_semestre")
    private Long idAnneeSemestre;
    
    @ManyToOne
    @JoinColumn(name = "id_semestre")
    private Semestre semestre;
    
    @ManyToOne
    @JoinColumn(name = "id_annee_universitaire")
    private AnneeUniversitaire anneeUniversitaire;
}
