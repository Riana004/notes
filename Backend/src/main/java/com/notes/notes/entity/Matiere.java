package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "matiere")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matiere {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matiere")
    private Long idMatiere;
    
    @Column(nullable = false, unique = true, length = 50)
    private String ue;
    
    @Column(nullable = false, length = 50)
    private String intitule;
    
    @OneToMany(mappedBy = "matiere")
    private List<SemestreMatiereParcours> semestreMatieresParcours;
    
    @OneToMany(mappedBy = "matiere")
    private List<Examen> examens;
}
