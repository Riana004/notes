package com.notes.notes.repository;

import com.notes.notes.entity.AnneeEtude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnneeEtudeRepository extends JpaRepository<AnneeEtude, Long> {
    Optional<AnneeEtude> findByNom(String nom);
}
