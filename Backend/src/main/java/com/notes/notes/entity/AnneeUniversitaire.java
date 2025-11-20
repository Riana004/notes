package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "annee_universitaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnneeUniversitaire {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "annee_debut", nullable = false)
    private Integer anneeDebut;
    
    @Column(name = "annee_fin", nullable = false)
    private Integer anneeFin;
    
    @OneToMany(mappedBy = "anneeUniversitaire")
    private List<Session> sessions;


}
