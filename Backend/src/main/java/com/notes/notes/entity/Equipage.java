package com.notes.notes.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "equipages")
public class Equipage {
    @Id
    @Column(name = "id_equipage")
    private String id;
    
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie", nullable = false)
    private Categorie categorie;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_personne", nullable = false)
    private Personne pilote;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_personne_1", nullable = false)
    private Personne copilote;
    
    @ManyToMany(mappedBy = "equipages")
    private List<Rally> rallyes = new ArrayList<>();
    
    @OneToMany(mappedBy = "equipage", cascade = CascadeType.ALL)
    private List<EquipageES> tempsSpeciales = new ArrayList<>();
    
    // Constructeurs
    public Equipage() {}
    
    public Equipage(String id, String nom, Categorie categorie, Personne pilote, Personne copilote) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.pilote = pilote;
        this.copilote = copilote;
    }
    
    // Getters et setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }
    public Personne getPilote() { return pilote; }
    public void setPilote(Personne pilote) { this.pilote = pilote; }
    public Personne getCopilote() { return copilote; }
    public void setCopilote(Personne copilote) { this.copilote = copilote; }
    public List<Rally> getRallyes() { return rallyes; }
    public void setRallyes(List<Rally> rallyes) { this.rallyes = rallyes; }
    public List<EquipageES> getTempsSpeciales() { return tempsSpeciales; }
    public void setTempsSpeciales(List<EquipageES> tempsSpeciales) { this.tempsSpeciales = tempsSpeciales; }
}