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
        
        // Pour S4, calculer les moyennes pour tous les parcours
        dto.setMoyennesS4(calculerMoyennesS4(etudiant.getIdEtudiant()));

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
        int sommeCredits = 0; // Somme de tous les crédits
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
                    BigDecimal note = notesMap.getOrDefault(smp.getMatiere().getIdMatiere(), BigDecimal.ZERO);
                    
                    NoteDetailDTO detail = new NoteDetailDTO();
                    detail.setUe(smp.getMatiere().getUe());
                    detail.setIntitule(smp.getMatiere().getIntitule());
                    detail.setCredit(smp.getCredit());
                    detail.setNote(note);
                    detail.setIsOption(false);
                    notes.add(detail);

                    // Compter tous les crédits
                    sommeCredits += smp.getCredit();
                    // Compter les crédits seulement si note >= 6
                    if (note.compareTo(new BigDecimal("6")) >= 0) {
                        totalCredits += smp.getCredit();
                    }
                    sommeNotesPonderees = sommeNotesPonderees.add(
                            note.multiply(BigDecimal.valueOf(smp.getCredit()))
                    );
                }
            } else {
                // Matières optionnelles : prendre celle avec la meilleure note
                SemestreMatiereParcours meilleureOption = null;
                BigDecimal meilleureNote = BigDecimal.ZERO;
                
                for (SemestreMatiereParcours smp : matieresGroup) {
                    BigDecimal note = notesMap.getOrDefault(smp.getMatiere().getIdMatiere(), BigDecimal.ZERO);
                    if (note.compareTo(meilleureNote) > 0) {
                        meilleureNote = note;
                        meilleureOption = smp;
                    }
                }

                // Si aucune note n'est trouvée, prendre la première matière du groupe
                if (meilleureOption == null && !matieresGroup.isEmpty()) {
                    meilleureOption = matieresGroup.get(0);
                    meilleureNote = BigDecimal.ZERO;
                }

                if (meilleureOption != null) {
                    NoteDetailDTO detail = new NoteDetailDTO();
                    detail.setUe(meilleureOption.getMatiere().getUe());
                    detail.setIntitule(meilleureOption.getMatiere().getIntitule());
                    detail.setCredit(meilleureOption.getCredit());
                    detail.setNote(meilleureNote);
                    detail.setIsOption(true);
                    notes.add(detail);

                    // Compter tous les crédits
                    sommeCredits += meilleureOption.getCredit();
                    // Compter les crédits seulement si note >= 6
                    if (meilleureNote.compareTo(new BigDecimal("6")) >= 0) {
                        totalCredits += meilleureOption.getCredit();
                    }
                    sommeNotesPonderees = sommeNotesPonderees.add(
                            meilleureNote.multiply(BigDecimal.valueOf(meilleureOption.getCredit()))
                    );
                }
            }
        }

        releve.setNotes(notes);
        releve.setTotalCredits(totalCredits);
        releve.setMoyenne(sommeCredits > 0 
                ? sommeNotesPonderees.divide(BigDecimal.valueOf(sommeCredits), 2, RoundingMode.HALF_UP)
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
        
        // Calculer les moyennes pour tous les parcours S4
        info.setMoyennesS4(calculerMoyennesS4(idEtudiant));

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

            // Pour S4, récupérer les notes de tous les parcours
            if (semestre.getLibellle().equals("S4")) {
                Map<String, List<NoteDetailDTO>> notesParParcours = new HashMap<>();
                List<String> parcoursS4 = Arrays.asList(
                    "Développement",
                    "Bases de Données et Réseaux",
                    "Web et Design"
                );
                
                for (String parcours : parcoursS4) {
                    try {
                        ReleveNoteDTO releve = getReleveNote(idEtudiant, semestre.getLibellle(), parcours);
                        notesParParcours.put(parcours, releve.getNotes());
                    } catch (Exception e) {
                        notesParParcours.put(parcours, new ArrayList<>());
                    }
                }
                dto.setNotesParParcours(notesParParcours);
            } else {
                // Pour S1, S2, S3, un seul parcours
                ReleveNoteDTO releve = getReleveNote(idEtudiant, semestre.getLibellle(), null);
                Map<String, List<NoteDetailDTO>> notesMap = new HashMap<>();
                notesMap.put("Connaissances Scientifiques et Techniques de Base", releve.getNotes());
                dto.setNotesParParcours(notesMap);
            }

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
     * Calculer les moyennes S4 pour tous les parcours
     */
    private Map<String, BigDecimal> calculerMoyennesS4(Long idEtudiant) {
        Map<String, BigDecimal> moyennes = new HashMap<>();
        
        // Liste des parcours S4
        List<String> parcoursS4 = Arrays.asList(
            "Développement",
            "Bases de Données et Réseaux",
            "Web et Design"
        );
        
        for (String parcours : parcoursS4) {
            BigDecimal moyenne = calculerMoyenneSemestre(idEtudiant, "S4", parcours);
            moyennes.put(parcours, moyenne);
        }
        
        return moyennes;
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
