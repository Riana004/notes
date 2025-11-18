package com.notes.notes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notes.notes.entity.Equipage;

@Repository
public interface EquipageRepository extends JpaRepository<Equipage, String> {
    List<Equipage> findByRallyesId(Long rallyId);
    List<Equipage> findByPiloteId(Long piloteId);
    List<Equipage> findByCopiloteId(Long copiloteId);
    List<Equipage> findByRallyesIdAndPiloteId(Long rallyId, Long piloteId);
}
