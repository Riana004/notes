package com.notes.notes.dto;

import java.util.List;

public class CategorieDTO {
    private Long id;
    private String nom; // Si vous ajoutez un nom à la catégorie
    private List<EquipageDTO> equipages;

    // Constructeurs
    public CategorieDTO() {
    }

    public CategorieDTO(Long id) {
        this.id = id;
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

    public List<EquipageDTO> getEquipages() {
        return equipages;
    }

    public void setEquipages(List<EquipageDTO> equipages) {
        this.equipages = equipages;
    }
}