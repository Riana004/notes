package com.notes.notes.repository;

import com.notes.notes.entity.Parcours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParcoursRepository extends JpaRepository<Parcours, Long> {
    Optional<Parcours> findByIntitule(String intitule);
}
