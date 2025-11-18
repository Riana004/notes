package com.notes.notes.dto;

import java.util.List;

public class RallyDTO {
    private Long id;
    private List<ESDTO> speciales;
    private List<EquipageDTO> equipages;

    // Constructeurs
    public RallyDTO() {
    }

    public RallyDTO(Long id) {
        this.id = id;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ESDTO> getSpeciales() {
        return speciales;
    }

    public void setSpeciales(List<ESDTO> speciales) {
        this.speciales = speciales;
    }

    public List<EquipageDTO> getEquipages() {
        return equipages;
    }

    public void setEquipages(List<EquipageDTO> equipages) {
        this.equipages = equipages;
    }
}
