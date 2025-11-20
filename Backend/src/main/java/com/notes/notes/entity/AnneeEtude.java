package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "annee_etude")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnneeEtude {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nom;
    
    @OneToMany(mappedBy = "anneeEtude")
    private List<Semestre> semestres;
}
