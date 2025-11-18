package com.notes.notes.dto;

import java.util.List;

public class EquipageDTO {
    private String id;
    private String nom;
    private Long categorieId;
    private String categorieNom;
    private PersonneDTO pilote;
    private PersonneDTO copilote;
    private List<RallyDTO> rallyes;
    private List<EquipageESDTO> tempsSpeciales;

    // Constructeurs
    public EquipageDTO() {
    }

    public EquipageDTO(String id, String nom, Long categorieId) {
        this.id = id;
        this.nom = nom;
        this.categorieId = categorieId;
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public String getCategorieNom() {
        return categorieNom;
    }

    public void setCategorieNom(String categorieNom) {
        this.categorieNom = categorieNom;
    }

    public PersonneDTO getPilote() {
        return pilote;
    }

    public void setPilote(PersonneDTO pilote) {
        this.pilote = pilote;
    }

    public PersonneDTO getCopilote() {
        return copilote;
    }

    public void setCopilote(PersonneDTO copilote) {
        this.copilote = copilote;
    }

    public List<RallyDTO> getRallyes() {
        return rallyes;
    }

    public void setRallyes(List<RallyDTO> rallyes) {
        this.rallyes = rallyes;
    }

    public List<EquipageESDTO> getTempsSpeciales() {
        return tempsSpeciales;
    }

    public void setTempsSpeciales(List<EquipageESDTO> tempsSpeciales) {
        this.tempsSpeciales = tempsSpeciales;
    }
}