package com.notes.notes.repository;

import com.notes.notes.entity.AnneeUniversitaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnneeUniversitaireRepository extends JpaRepository<AnneeUniversitaire, Long> {
    Optional<AnneeUniversitaire> findByIntitule(String intitule);
}

