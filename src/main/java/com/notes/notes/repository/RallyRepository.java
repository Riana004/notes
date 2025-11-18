package com.notes.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notes.notes.entity.Rally;

@Repository
public interface RallyRepository extends JpaRepository<Rally, Long> {
}