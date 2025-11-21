package com.notes.notes.repository;

import com.notes.notes.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
    List<Examen> findByEtudiantIdEtudiant(Long idEtudiant);
    List<Examen> findByEtudiantIdEtudiantAndMatiereIdMatiere(Long idEtudiant, Long idMatiere);
    
    @Query("SELECT e FROM Examen e WHERE e.etudiant.idEtudiant = :idEtudiant AND e.matiere.idMatiere = :idMatiere")
    Optional<Examen> findNoteByEtudiantAndMatiere(@Param("idEtudiant") Long idEtudiant, @Param("idMatiere") Long idMatiere);
}
