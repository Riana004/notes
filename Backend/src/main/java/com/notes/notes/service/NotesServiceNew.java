package com.notes.notes.service;

import com.notes.notes.dto.*;
import com.notes.notes.entity.*;
import com.notes.notes.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotesService {

    private final EtudiantRepository etudiantRepository;
    private final SemestreRepository semestreRepository;
    private final MatiereRepository matiereRepository;
    private final ExamenRepository examenRepository;
    private final SemestreMatieresParcoursRepository semestreMatieresParcoursRepository;
    private final InscriptionSemestreRepository inscriptionSemestreRepository;
    private final ParcoursRepository parcoursRepository;
    private final AnneeUniversitaireRepository anneeUniversitaireRepository;
    private final AnneeSemestreRepository anneeSemestreRepository;

    /**
     * Liste de tous les semestres
     */
    public List<SemestreDTO> getAllSemestres() {
        return semestreRepository.findAll().stream()
                .map(s -> new SemestreDTO(s.getIdSemestre(), s.getLibellle()))
                .collect(Collectors.toList());
    }

    /**
     * Liste des étudiants avec leurs moyennes S1 à S4
     */
    public List<EtudiantMoyennesDTO> getEtudiantsAvecMoyennes() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        return etudiants.stream()
                .map(this::calculerMoyennesEtudiant)
                .collect(Collectors.toList());
    }

    private EtudiantMoyennesDTO calculerMoyennesEtudiant(Etudiant etudiant) {
        EtudiantMoyennesDTO dto = new EtudiantMoyennesDTO();
        dto.setIdEtudiant(etudiant.getIdEtudiant());
        dto.setMatricule(etudiant.getMatricule());
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());

        // Calculer les moyennes pour chaque semestre
        dto.setMoyenneS1(calculerMoyenneSemestre(etudiant.getIdEtudiant(), "S1", null));
        dto.setMoyenneS2(calculerMoyenneSemestre(etudiant.getIdEtudiant(), "S2", null));
        dto.setMoyenneS3(calculerMoyenneSemestre(etudiant.getIdEtudiant(), "S3", null));
        
        // Pour S4, déterminer le parcours de l'étudiant
        String parcoursS4 = determinerParcoursS4(etudiant.getIdEtudiant());
        dto.setParcoursS4(parcoursS4);
        dto.setMoyenneS4(calculerMoyenneSemestre(etudiant.getIdEtudiant(), "S4", parcoursS4));

        return dto;
    }

    /**
     * Relevé de note d'un étudiant pour un semestre donné
     */
    public ReleveNoteDTO getReleveNote(Long idEtudiant, String semestre, String parcours) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        
        Semestre sem = semestreRepository.findByLibellle(semestre)
                .orElseThrow(() -> new RuntimeException("Semestre non trouvé"));

        ReleveNoteDTO releve = new ReleveNoteDTO();
        releve.setIdEtudiant(etudiant.getIdEtudiant());
        releve.setMatricule(etudiant.getMatricule());
        releve.setNom(etudiant.getNom());
        releve.setPrenom(etudiant.getPrenom());
        releve.setSemestre(semestre);
        releve.setParcours(parcours);

        List<NoteDetailDTO> notes = new ArrayList<>();
        int totalCredits = 0;
        BigDecimal sommeNotesPonderees = BigDecimal.ZERO;

        // Récupérer les matières du semestre en fonction du parcours
        List<SemestreMatiereParcours> matieres;
        if (parcours != null && semestre.equals("S4")) {
            Parcours p = parcoursRepository.findByIntitule(parcours)
                    .orElseThrow(() -> new RuntimeException("Parcours non trouvé"));
            matieres = semestreMatieresParcoursRepository
                    .findBySemestreIdSemestreAndParcoursIdParcours(sem.getIdSemestre(), p.getIdParcours());
        } else {
            // Pour S1, S2, S3, prendre le parcours "Connaissances Scientifiques et Techniques de Base"
            Parcours p = parcoursRepository.findByIntitule("Connaissances Scientifiques et Techniques de Base")
                    .orElseThrow(() -> new RuntimeException("Parcours de base non trouvé"));
            matieres = semestreMatieresParcoursRepository
                    .findBySemestreIdSemestreAndParcoursIdParcours(sem.getIdSemestre(), p.getIdParcours());
        }

        // Récupérer les notes de l'étudiant pour ces matières
        List<Examen> examens = examenRepository.findByEtudiantIdEtudiant(idEtudiant);
        Map<Long, BigDecimal> notesMap = examens.stream()
                .collect(Collectors.toMap(
                        e -> e.getMatiere().getIdMatiere(),
                        Examen::getNote,
                        (e1, e2) -> e1
                ));

        // Traiter les matières avec option
        Map<Integer, List<SemestreMatiereParcours>> matieresParOption = matieres.stream()
                .collect(Collectors.groupingBy(SemestreMatiereParcours::getOption));

        for (Map.Entry<Integer, List<SemestreMatiereParcours>> entry : matieresParOption.entrySet()) {
            Integer optionGroup = entry.getKey();
            List<SemestreMatiereParcours> matieresGroup = entry.getValue();

            if (optionGroup == 0) {
                // Matières obligatoires
                for (SemestreMatiereParcours smp : matieresGroup) {
                    BigDecimal note = notesMap.get(smp.getMatiere().getIdMatiere());
                    if (note != null) {
                        NoteDetailDTO detail = new NoteDetailDTO();
                        detail.setUe(smp.getMatiere().getUe());
                        detail.setIntitule(smp.getMatiere().getIntitule());
                        detail.setCredit(smp.getCredit());
                        detail.setNote(note);
                        detail.setIsOption(false);
                        notes.add(detail);

                        totalCredits += smp.getCredit();
                        sommeNotesPonderees = sommeNotesPonderees.add(
                                note.multiply(BigDecimal.valueOf(smp.getCredit()))
                        );
                    }
                }
            } else {
                // Matières optionnelles : prendre celle avec la meilleure note
                SemestreMatiereParcours meilleureOption = null;
                BigDecimal meilleureNote = BigDecimal.ZERO;
                
                for (SemestreMatiereParcours smp : matieresGroup) {
                    BigDecimal note = notesMap.get(smp.getMatiere().getIdMatiere());
                    if (note != null && note.compareTo(meilleureNote) > 0) {
                        meilleureNote = note;
                        meilleureOption = smp;
                    }
                }

                if (meilleureOption != null) {
                    NoteDetailDTO detail = new NoteDetailDTO();
                    detail.setUe(meilleureOption.getMatiere().getUe());
                    detail.setIntitule(meilleureOption.getMatiere().getIntitule());
                    detail.setCredit(meilleureOption.getCredit());
                    detail.setNote(meilleureNote);
                    detail.setIsOption(true);
                    notes.add(detail);

                    totalCredits += meilleureOption.getCredit();
                    sommeNotesPonderees = sommeNotesPonderees.add(
                            meilleureNote.multiply(BigDecimal.valueOf(meilleureOption.getCredit()))
                    );
                }
            }
        }

        releve.setNotes(notes);
        releve.setTotalCredits(totalCredits);
        releve.setMoyenne(totalCredits > 0 
                ? sommeNotesPonderees.divide(BigDecimal.valueOf(totalCredits), 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO);

        return releve;
    }

    /**
     * Info d'un étudiant avec moyennes S1 à S4
     */
    public EtudiantInfoDTO getEtudiantInfo(Long idEtudiant) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        EtudiantInfoDTO info = new EtudiantInfoDTO();
        info.setIdEtudiant(etudiant.getIdEtudiant());
        info.setMatricule(etudiant.getMatricule());
        info.setNom(etudiant.getNom());
        info.setPrenom(etudiant.getPrenom());
        info.setMoyenneS1(calculerMoyenneSemestre(idEtudiant, "S1", null));
        info.setMoyenneS2(calculerMoyenneSemestre(idEtudiant, "S2", null));
        info.setMoyenneS3(calculerMoyenneSemestre(idEtudiant, "S3", null));
        
        String parcoursS4 = determinerParcoursS4(idEtudiant);
        info.setMoyenneS4(calculerMoyenneSemestre(idEtudiant, "S4", parcoursS4));

        return info;
    }

    /**
     * Notes par semestre pour une année universitaire (L1, L2) d'un étudiant
     */
    public List<NotesSemestreAnneeDTO> getNotesSemestreParAnnee(Long idEtudiant, String anneeUniversitaire) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        AnneeUniversitaire annee = anneeUniversitaireRepository.findByIntitule(anneeUniversitaire)
                .orElseThrow(() -> new RuntimeException("Année universitaire non trouvée"));

        List<NotesSemestreAnneeDTO> result = new ArrayList<>();

        // Récupérer les semestres de cette année
        List<AnneeSemestre> anneeSemestres = anneeSemestreRepository
                .findByAnneeUniversitaireIdAnneeUniversitaire(annee.getIdAnneeUniversitaire());

        for (AnneeSemestre as : anneeSemestres) {
            Semestre semestre = as.getSemestre();
            
            NotesSemestreAnneeDTO dto = new NotesSemestreAnneeDTO();
            dto.setIdEtudiant(etudiant.getIdEtudiant());
            dto.setMatricule(etudiant.getMatricule());
            dto.setNom(etudiant.getNom());
            dto.setPrenom(etudiant.getPrenom());
            dto.setAnneeUniversitaire(anneeUniversitaire);
            dto.setSemestre(semestre.getLibellle());

            // Récupérer les notes pour ce semestre
            String parcours = null;
            if (semestre.getLibellle().equals("S4")) {
                parcours = determinerParcoursS4(idEtudiant);
            }
            
            ReleveNoteDTO releve = getReleveNote(idEtudiant, semestre.getLibellle(), parcours);
            dto.setNotes(releve.getNotes());

            result.add(dto);
        }

        return result;
    }

    /**
     * Calculer la moyenne d'un semestre pour un étudiant
     */
    private BigDecimal calculerMoyenneSemestre(Long idEtudiant, String semestre, String parcours) {
        try {
            ReleveNoteDTO releve = getReleveNote(idEtudiant, semestre, parcours);
            return releve.getMoyenne();
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    /**
     * Déterminer le parcours S4 d'un étudiant en fonction des matières pour lesquelles il a des notes
     */
    private String determinerParcoursS4(Long idEtudiant) {
        // Récupérer toutes les notes de l'étudiant
        List<Examen> examens = examenRepository.findByEtudiantIdEtudiant(idEtudiant);
        
        // Récupérer les matières de S4
        Semestre s4 = semestreRepository.findByLibellle("S4").orElse(null);
        if (s4 == null) {
            return null;
        }

        // Compter les notes par parcours pour S4
        Map<String, Long> countParParcours = new HashMap<>();
        
        for (Examen examen : examens) {
            List<SemestreMatiereParcours> smps = semestreMatieresParcoursRepository
                    .findBySemestreIdSemestre(s4.getIdSemestre());
            
            for (SemestreMatiereParcours smp : smps) {
                if (smp.getMatiere().getIdMatiere().equals(examen.getMatiere().getIdMatiere())) {
                    String parcoursName = smp.getParcours().getIntitule();
                    if (!parcoursName.equals("Connaissances Scientifiques et Techniques de Base")) {
                        countParParcours.put(parcoursName, countParParcours.getOrDefault(parcoursName, 0L) + 1);
                    }
                }
            }
        }

        // Retourner le parcours avec le plus de notes
        return countParParcours.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
