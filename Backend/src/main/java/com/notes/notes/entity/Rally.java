package com.notes.notes.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "rally")
public class Rally {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rally")
    private Long id;
    
    @OneToMany(mappedBy = "rally", cascade = CascadeType.ALL)
    private List<ES> speciales = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
        name = "equipage_rally",
        joinColumns = @JoinColumn(name = "id_rally"),
        inverseJoinColumns = @JoinColumn(name = "id_equipage")
    )
    private List<Equipage> equipages = new ArrayList<>();
    
    // Constructeurs
    public Rally() {}
    
    public Rally(Long id) {
        this.id = id;
    }
    
    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public List<ES> getSpeciales() { return speciales; }
    public void setSpeciales(List<ES> speciales) { this.speciales = speciales; }
    public List<Equipage> getEquipages() { return equipages; }
    public void setEquipages(List<Equipage> equipages) { this.equipages = equipages; }
}
