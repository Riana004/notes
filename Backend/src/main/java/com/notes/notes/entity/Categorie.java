package com.notes.notes.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorie")
    private Long id;
    
    @OneToMany(mappedBy = "categorie")
    private List<Equipage> equipages = new ArrayList<>();
    
    // Constructeurs
    public Categorie() {}
    
    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public List<Equipage> getEquipages() { return equipages; }
    public void setEquipages(List<Equipage> equipages) { this.equipages = equipages; }
}
