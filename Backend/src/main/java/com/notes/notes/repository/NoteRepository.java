package com.notes.notes.repository;

import com.notes.notes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    
    @Query("SELECT n FROM Note n " +
           "JOIN n.matiere m " +
           "JOIN m.semestre s " +
           "WHERE n.etudiant.id = :etudiantId " +
           "AND s.nom = :semestreNom")
    List<Note> findByEtudiantIdAndSemestreNom(@Param("etudiantId") Long etudiantId, 
                                               @Param("semestreNom") String semestreNom);
    
    @Query("SELECT n FROM Note n " +
           "JOIN n.matiere m " +
           "JOIN m.semestre s " +
           "JOIN s.anneeEtude ae " +
           "WHERE n.etudiant.id = :etudiantId " +
           "AND ae.nom = :anneeNom")
    List<Note> findByEtudiantIdAndAnneeNom(@Param("etudiantId") Long etudiantId, 
                                            @Param("anneeNom") String anneeNom);
}
