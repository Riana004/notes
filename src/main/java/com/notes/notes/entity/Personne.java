package com.notes.notes.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "personnes")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personne")
    private Long id;
    
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @Column(name = "prenom", nullable = false)
    private String prenom;
    
    @OneToMany(mappedBy = "pilote")
    private List<Equipage> equipagesPilote = new ArrayList<>();
    
    @OneToMany(mappedBy = "copilote")
    private List<Equipage> equipagesCopilote = new ArrayList<>();
    
    // Constructeurs
    public Personne() {}
    
    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public List<Equipage> getEquipagesPilote() { return equipagesPilote; }
    public void setEquipagesPilote(List<Equipage> equipagesPilote) { this.equipagesPilote = equipagesPilote; }
    public List<Equipage> getEquipagesCopilote() { return equipagesCopilote; }
    public void setEquipagesCopilote(List<Equipage> equipagesCopilote) { this.equipagesCopilote = equipagesCopilote; }
}
