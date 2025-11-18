package com.notes.notes.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notes.notes.dto.CategorieDTO;
import com.notes.notes.dto.ESDTO;
import com.notes.notes.dto.EquipageESDTO;
import com.notes.notes.dto.PersonneDTO;
import com.notes.notes.dto.RallyDTO;
import com.notes.notes.entity.Equipage;
import com.notes.notes.repository.CategorieRepository;
import com.notes.notes.repository.ESRepository;
import com.notes.notes.repository.EquipageESRepository;
import com.notes.notes.repository.EquipageRepository;
import com.notes.notes.repository.RallyRepository;
import com.notes.notes.mapper.RallyMapper;

@Service
@Transactional
public class RallyService {

    @Autowired
    private RallyRepository rallyRepository;

    @Autowired
    private ESRepository esRepository;

    @Autowired
    private EquipageRepository equipageRepository;

    @Autowired
    private EquipageESRepository equipageESRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private RallyMapper mapper;

    public List<RallyDTO> getAllRallyes() {
        return rallyRepository.findAll().stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    public List<CategorieDTO> getAllCategories() {
        return categorieRepository.findAll().stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    public Optional<RallyDTO> getRallyById(Long id) {
        return rallyRepository.findById(id).map(mapper::toDTO);
    }

    public List<ESDTO> getSpecialesByRally(Long rallyId) {
        return esRepository.findByRallyId(rallyId).stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    public List<EquipageESDTO> getTempsBySpeciale(Long specialeId) {
        return equipageESRepository.findByEsId(specialeId).stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    public List<PersonneDTO> getPilotesByRally(Long rallyId) {
        List<Equipage> equipages = equipageRepository.findByRallyesId(rallyId);
        return equipages.stream()
                .map(Equipage::getPilote)
                .distinct()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EquipageESDTO> getTempsPiloteSpeciale(Long rallyId, Long specialeId, Long piloteId) {
        // Trouver l'équipage du pilote pour ce rally
        List<Equipage> equipages = equipageRepository.findByRallyesIdAndPiloteId(rallyId, piloteId);
        if (equipages.isEmpty()) {
            return Optional.empty();
        }

        String equipageId = equipages.get(0).getId();
        return equipageESRepository.findByEsIdAndEquipageId(specialeId, equipageId)
                .map(mapper::toDTO);
    }

    public List<EquipageESDTO> getTempsPiloteRally(Long rallyId, Long piloteId) {
        List<Equipage> equipages = equipageRepository.findByRallyesIdAndPiloteId(rallyId, piloteId);
        if (equipages.isEmpty()) {
            return Collections.emptyList();
        }

        String equipageId = equipages.get(0).getId();
        return equipageESRepository.findByEquipageIdAndEsRallyId(equipageId, rallyId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    // Méthode personnalisée pour findByRallyesIdAndPiloteId
    public List<Equipage> findByRallyesIdAndPiloteId(Long rallyId, Long piloteId) {
        return equipageRepository.findByRallyesId(rallyId).stream()
                .filter(e -> e.getPilote().getId().equals(piloteId))
                .collect(Collectors.toList());
    }
}
