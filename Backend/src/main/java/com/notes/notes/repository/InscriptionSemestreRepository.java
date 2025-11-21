package com.notes.notes.repository;

import com.notes.notes.entity.InscriptionSemestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscriptionSemestreRepository extends JpaRepository<InscriptionSemestre, Long> {
    List<InscriptionSemestre> findByEtudiantIdEtudiant(Long idEtudiant);
    List<InscriptionSemestre> findByEtudiantIdEtudiantAndSemestreIdSemestre(Long idEtudiant, Long idSemestre);
}
