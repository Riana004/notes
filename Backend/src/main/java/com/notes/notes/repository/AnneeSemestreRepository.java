package com.notes.notes.repository;

import com.notes.notes.entity.AnneeSemestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnneeSemestreRepository extends JpaRepository<AnneeSemestre, Long> {
    List<AnneeSemestre> findByAnneeUniversitaireIdAnneeUniversitaire(Long idAnneeUniversitaire);
    Optional<AnneeSemestre> findBySemestreIdSemestreAndAnneeUniversitaireIdAnneeUniversitaire(Long idSemestre, Long idAnneeUniversitaire);
}
