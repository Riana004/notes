package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "semestre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Semestre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_annee_etude", nullable = false)
    private AnneeEtude anneeEtude;
    
    @Column(nullable = false, length = 100)
    private String nom;
    
    @OneToMany(mappedBy = "semestre")
    private List<Matiere> matieres;
}
