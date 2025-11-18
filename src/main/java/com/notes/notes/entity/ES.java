package com.notes.notes.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "es")
public class ES {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ides")
    private Long id;
    
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @Column(name = "distance", nullable = false)
    private Double distance;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rally")
    private Rally rally;
    
    @OneToMany(mappedBy = "es", cascade = CascadeType.ALL)
    private List<EquipageES> tempsEquipages = new ArrayList<>();
    
    // Constructeurs
    public ES() {}
    
    public ES(String nom, Double distance, Rally rally) {
        this.nom = nom;
        this.distance = distance;
        this.rally = rally;
    }
    
    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Double getDistance() { return distance; }
    public void setDistance(Double distance) { this.distance = distance; }
    public Rally getRally() { return rally; }
    public void setRally(Rally rally) { this.rally = rally; }
    public List<EquipageES> getTempsEquipages() { return tempsEquipages; }
    public void setTempsEquipages(List<EquipageES> tempsEquipages) { this.tempsEquipages = tempsEquipages; }
}
