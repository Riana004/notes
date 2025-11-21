package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "parcours")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parcours {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parcours")
    private Long idParcours;
    
    @Column(nullable = false, length = 50)
    private String intitule;
    
    @OneToMany(mappedBy = "parcours")
    private List<SemestreMatiereParcours> semestreMatieresParcours;
}
