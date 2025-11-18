package com.notes.notes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notes.notes.entity.ES;

@Repository
public interface ESRepository extends JpaRepository<ES, Long> {
    List<ES> findByRallyId(Long rallyId);
    Optional<ES> findByIdAndRallyId(Long id, Long rallyId);
}