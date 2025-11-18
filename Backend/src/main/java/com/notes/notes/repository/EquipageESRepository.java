package com.notes.notes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notes.notes.entity.EquipageES;
@Repository
public interface EquipageESRepository extends JpaRepository<EquipageES, Long> {
    List<EquipageES> findByEsId(Long esId);
    List<EquipageES> findByEquipageId(String equipageId);
    Optional<EquipageES> findByEsIdAndEquipageId(Long esId, String equipageId);
    List<EquipageES> findByEsRallyId(Long rallyId);
    List<EquipageES> findByEquipageIdAndEsRallyId(String equipageId, Long rallyId);
    
    // Vérifier si une association existe déjà
    boolean existsByEsIdAndEquipageId(Long esId, String equipageId);
}