package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "session")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "periode_annee", nullable = false)
    private Integer periodeAnnee;
    
    @Column(name = "periode_mois", nullable = false)
    private Integer periodeMois;
    
    @ManyToOne
    @JoinColumn(name = "id_annee_universitaire", nullable = false)
    private AnneeUniversitaire anneeUniversitaire;
    
    @OneToMany(mappedBy = "session")
    private List<Note> notes;
}
