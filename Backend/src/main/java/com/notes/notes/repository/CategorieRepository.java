package com.notes.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notes.notes.entity.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
