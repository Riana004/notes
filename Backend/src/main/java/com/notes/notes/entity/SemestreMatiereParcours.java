package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "semestrematiereparcours")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemestreMatiereParcours {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_semestre_matiere_parcours")
    private Long idSemestreMatiereParcours;
    
    @Column(nullable = false)
    private Integer credit;
    
    @Column(nullable = false)
    private Integer option;
    
    @ManyToOne
    @JoinColumn(name = "id_semestre")
    private Semestre semestre;
    
    @ManyToOne
    @JoinColumn(name = "id_matiere")
    private Matiere matiere;
    
    @ManyToOne
    @JoinColumn(name = "id_parcours")
    private Parcours parcours;
}
