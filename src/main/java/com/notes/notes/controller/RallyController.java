package com.notes.notes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.notes.notes.dto.CategorieDTO;
import com.notes.notes.dto.ESDTO;
import com.notes.notes.dto.EquipageESDTO;
import com.notes.notes.dto.PersonneDTO;
import com.notes.notes.dto.RallyDTO;
import com.notes.notes.service.RallyService;

@RestController
public class RallyController {
    
    @Autowired
    private RallyService rallyService;
    
    // GET /rallyes
    @GetMapping("/rallyes")
    public ResponseEntity<List<RallyDTO>> getAllRallyes() {
        List<RallyDTO> rallyes = rallyService.getAllRallyes();
        return ResponseEntity.ok(rallyes);
    }
    
    // GET /rallyes/{rallye_id}/speciales
    @GetMapping("/rallyes/{rallyId}/speciales")
    public ResponseEntity<List<ESDTO>> getSpecialesByRally(@PathVariable Long rallyId) {
        List<ESDTO> speciales = rallyService.getSpecialesByRally(rallyId);
        return ResponseEntity.ok(speciales);
    }
    
    // GET /rallyes/{rallye_id}/speciales/{speciale_id}/temps
    @GetMapping("/rallyes/{rallyId}/speciales/{specialeId}/temps")
    public ResponseEntity<List<EquipageESDTO>> getTempsBySpeciale(
            @PathVariable Long rallyId,
            @PathVariable Long specialeId) {
        
        List<EquipageESDTO> temps = rallyService.getTempsBySpeciale(specialeId);
        return ResponseEntity.ok(temps);
    }
    
    // GET /categories
    @GetMapping("/categories")
    public ResponseEntity<List<CategorieDTO>> getAllCategories() {
        List<CategorieDTO> categories = rallyService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    
    // GET /rallyes/{rallye_id}/pilotes
    @GetMapping("/rallyes/{rallyId}/pilotes")
    public ResponseEntity<List<PersonneDTO>> getPilotesByRally(@PathVariable Long rallyId) {
        List<PersonneDTO> pilotes = rallyService.getPilotesByRally(rallyId);
        return ResponseEntity.ok(pilotes);
    }
    
    // GET /rallyes/{rallye_id}/speciales/{speciale_id}/pilotes/{pilote_id}/temps
    @GetMapping("/rallyes/{rallyId}/speciales/{specialeId}/pilotes/{piloteId}/temps")
    public ResponseEntity<EquipageESDTO> getTempsPiloteSpeciale(
            @PathVariable Long rallyId,
            @PathVariable Long specialeId,
            @PathVariable Long piloteId) {
        
        Optional<EquipageESDTO> temps = rallyService.getTempsPiloteSpeciale(rallyId, specialeId, piloteId);
        return temps.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    // GET /rallyes/{rallye_id}/pilotes/{pilote_id}/temps
    @GetMapping("/rallyes/{rallyId}/pilotes/{piloteId}/temps")
    public ResponseEntity<List<EquipageESDTO>> getTempsPiloteRally(
            @PathVariable Long rallyId,
            @PathVariable Long piloteId) {
        
        List<EquipageESDTO> temps = rallyService.getTempsPiloteRally(rallyId, piloteId);
        return ResponseEntity.ok(temps);
    }
}