package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "inscriptionsemestre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionSemestre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscriptionsemestre")
    private Long idInscriptionSemestre;
    
    @Column(name = "date_inscription", nullable = false)
    private LocalDate dateInscription;
    
    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;
    
    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiant etudiant;
    
    @ManyToOne
    @JoinColumn(name = "id_semestre")
    private Semestre semestre;
}
