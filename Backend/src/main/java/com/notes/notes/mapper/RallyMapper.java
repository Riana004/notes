package com.notes.notes.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.notes.notes.dto.CategorieDTO;
import com.notes.notes.dto.ESDTO;
import com.notes.notes.dto.EquipageDTO;
import com.notes.notes.dto.EquipageESDTO;
import com.notes.notes.dto.PersonneDTO;
import com.notes.notes.dto.RallyDTO;
import com.notes.notes.entity.Categorie;
import com.notes.notes.entity.ES;
import com.notes.notes.entity.Equipage;
import com.notes.notes.entity.EquipageES;
import com.notes.notes.entity.Personne;
import com.notes.notes.entity.Rally;

@Component
public class RallyMapper {
    
    public RallyDTO toDTO(Rally rally) {
        if (rally == null) return null;
        
        RallyDTO dto = new RallyDTO();
        dto.setId(rally.getId());
        
        if (rally.getSpeciales() != null) {
            dto.setSpeciales(rally.getSpeciales().stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
        }
        
        if (rally.getEquipages() != null) {
            dto.setEquipages(rally.getEquipages().stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    public ESDTO toDTO(ES es) {
        if (es == null) return null;
        
        ESDTO dto = new ESDTO();
        dto.setId(es.getId());
        dto.setNom(es.getNom());
        dto.setDistance(es.getDistance());
        
        if (es.getRally() != null) {
            dto.setRallyId(es.getRally().getId());
        }
        
        if (es.getTempsEquipages() != null) {
            dto.setTempsEquipages(es.getTempsEquipages().stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    public CategorieDTO toDTO(Categorie categorie) {
        if (categorie == null) return null;
        
        CategorieDTO dto = new CategorieDTO();
        dto.setId(categorie.getId());
        
        if (categorie.getEquipages() != null) {
            dto.setEquipages(categorie.getEquipages().stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    public PersonneDTO toDTO(Personne personne) {
        if (personne == null) return null;
        
        PersonneDTO dto = new PersonneDTO();
        dto.setId(personne.getId());
        dto.setNom(personne.getNom());
        dto.setPrenom(personne.getPrenom());
        
        return dto;
    }
    
    public EquipageDTO toDTO(Equipage equipage) {
        if (equipage == null) return null;
        
        EquipageDTO dto = new EquipageDTO();
        dto.setId(equipage.getId());
        dto.setNom(equipage.getNom());
        
        if (equipage.getCategorie() != null) {
            dto.setCategorieId(equipage.getCategorie().getId());
        }
        
        if (equipage.getPilote() != null) {
            dto.setPilote(toDTO(equipage.getPilote()));
        }
        
        if (equipage.getCopilote() != null) {
            dto.setCopilote(toDTO(equipage.getCopilote()));
        }
        
        if (equipage.getTempsSpeciales() != null) {
            dto.setTempsSpeciales(equipage.getTempsSpeciales().stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    public EquipageESDTO toDTO(EquipageES equipageES) {
        if (equipageES == null) return null;
        
        EquipageESDTO dto = new EquipageESDTO();
        dto.setId(equipageES.getId());
        dto.setTemps(equipageES.getTemps());
        
        if (equipageES.getEs() != null) {
            dto.setEsId(equipageES.getEs().getId());
            dto.setEsNom(equipageES.getEs().getNom());
            dto.setEsDistance(equipageES.getEs().getDistance());
        }
        
        if (equipageES.getEquipage() != null) {
            dto.setEquipageId(equipageES.getEquipage().getId());
            dto.setEquipageNom(equipageES.getEquipage().getNom());
        }
        
        return dto;
    }
}
