package com.notes.notes.entity;

import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name = "equipage_es")
public class EquipageES {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ides", nullable = false)
    private ES es;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipage", nullable = false)
    private Equipage equipage;
    
    @Column(name = "temps")
    private LocalTime temps;
    
    // Constructeurs
    public EquipageES() {}
    
    public EquipageES(ES es, Equipage equipage, LocalTime temps) {
        this.es = es;
        this.equipage = equipage;
        this.temps = temps;
    }
    
    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ES getEs() { return es; }
    public void setEs(ES es) { this.es = es; }
    public Equipage getEquipage() { return equipage; }
    public void setEquipage(Equipage equipage) { this.equipage = equipage; }
    public LocalTime getTemps() { return temps; }
    public void setTemps(LocalTime temps) { this.temps = temps; }
}