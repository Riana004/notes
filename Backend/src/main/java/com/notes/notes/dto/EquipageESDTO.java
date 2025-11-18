package com.notes.notes.dto;

import java.time.LocalTime;

public class EquipageESDTO {
    private Long id;
    private Long esId;
    private String esNom;
    private Double esDistance;
    private String equipageId;
    private String equipageNom;
    private LocalTime temps;

    // Constructeurs
    public EquipageESDTO() {
    }

    public EquipageESDTO(Long id, Long esId, String equipageId, LocalTime temps) {
        this.id = id;
        this.esId = esId;
        this.equipageId = equipageId;
        this.temps = temps;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEsId() {
        return esId;
    }

    public void setEsId(Long esId) {
        this.esId = esId;
    }

    public String getEsNom() {
        return esNom;
    }

    public void setEsNom(String esNom) {
        this.esNom = esNom;
    }

    public Double getEsDistance() {
        return esDistance;
    }

    public void setEsDistance(Double esDistance) {
        this.esDistance = esDistance;
    }

    public String getEquipageId() {
        return equipageId;
    }

    public void setEquipageId(String equipageId) {
        this.equipageId = equipageId;
    }

    public String getEquipageNom() {
        return equipageNom;
    }

    public void setEquipageNom(String equipageNom) {
        this.equipageNom = equipageNom;
    }

    public LocalTime getTemps() {
        return temps;
    }

    public void setTemps(LocalTime temps) {
        this.temps = temps;
    }
}