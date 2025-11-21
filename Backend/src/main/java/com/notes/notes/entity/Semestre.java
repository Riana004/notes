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
    @Column(name = "id_semestre")
    private Long idSemestre;
    
    @Column(nullable = false, length = 50)
    private String libellle;
    
    @OneToMany(mappedBy = "semestre")
    private List<InscriptionSemestre> inscriptions;
    
    @OneToMany(mappedBy = "semestre")
    private List<AnneeSemestre> anneeSemestres;
    
    @OneToMany(mappedBy = "semestre")
    private List<SemestreMatiereParcours> semestreMatieresParcours;
}
