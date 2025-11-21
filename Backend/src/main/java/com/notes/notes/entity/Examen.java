package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "examen")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Examen {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_examen")
    private Long idExamen;
    
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal note;
    
    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiant etudiant;
    
    @ManyToOne
    @JoinColumn(name = "id_matiere")
    private Matiere matiere;
    
    @ManyToOne
    @JoinColumn(name = "id_session")
    private Session session;
}
