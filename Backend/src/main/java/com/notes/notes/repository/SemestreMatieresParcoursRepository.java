package com.notes.notes.repository;

import com.notes.notes.entity.SemestreMatiereParcours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemestreMatieresParcoursRepository extends JpaRepository<SemestreMatiereParcours, Long> {
    List<SemestreMatiereParcours> findBySemestreIdSemestre(Long idSemestre);
    List<SemestreMatiereParcours> findBySemestreIdSemestreAndParcoursIdParcours(Long idSemestre, Long idParcours);
    
    @Query("SELECT smp FROM SemestreMatiereParcours smp WHERE smp.semestre.idSemestre = :idSemestre")
    List<SemestreMatiereParcours> findMatieresParSemestre(@Param("idSemestre") Long idSemestre);
}
