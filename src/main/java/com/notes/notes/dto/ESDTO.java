package com.notes.notes.dto;

import java.util.List;

public class ESDTO {
    private Long id;
    private String nom;
    private Double distance;
    private Long rallyId;
    private String rallyNom;
    private List<EquipageESDTO> tempsEquipages;

    // Constructeurs
    public ESDTO() {
    }

    public ESDTO(Long id, String nom, Double distance, Long rallyId) {
        this.id = id;
        this.nom = nom;
        this.distance = distance;
        this.rallyId = rallyId;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Long getRallyId() {
        return rallyId;
    }

    public void setRallyId(Long rallyId) {
        this.rallyId = rallyId;
    }

    public String getRallyNom() {
        return rallyNom;
    }

    public void setRallyNom(String rallyNom) {
        this.rallyNom = rallyNom;
    }

    public List<EquipageESDTO> getTempsEquipages() {
        return tempsEquipages;
    }

    public void setTempsEquipages(List<EquipageESDTO> tempsEquipages) {
        this.tempsEquipages = tempsEquipages;
    }
}