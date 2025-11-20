package com.notes.notes.service;

import com.notes.notes.dto.NoteDTO;
import com.notes.notes.dto.NotesResponse;
import com.notes.notes.entity.Etudiant;
import com.notes.notes.entity.Note;
import com.notes.notes.exception.DatabaseException;
import com.notes.notes.exception.EtudiantNotFoundException;
import com.notes.notes.exception.NotesNotFoundException;
import com.notes.notes.repository.EtudiantRepository;
import com.notes.notes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotesService {
    
    private final NoteRepository noteRepository;
    private final EtudiantRepository etudiantRepository;
    
    public NotesResponse getNotesBySemestre(String etu, String semestreNom) {
        try {
            Etudiant etudiant = etudiantRepository.findByEtu(etu)
                    .orElseThrow(() -> new EtudiantNotFoundException("Aucun étudiant trouvé avec le numéro ETU: " + etu));
            
            List<Note> notes = noteRepository.findByEtudiantIdAndSemestreNom(etudiant.getId(), semestreNom);
            
            if (notes.isEmpty()) {
                throw new NotesNotFoundException("Aucune note trouvée pour le semestre: " + semestreNom);
            }
            
            return buildNotesResponse(etudiant, notes, semestreNom);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Erreur lors de l'accès à la base de données", ex);
        }
    }
    
    public NotesResponse getNotesByAnnee(String etu, String anneeNom) {
        try {
            Etudiant etudiant = etudiantRepository.findByEtu(etu)
                    .orElseThrow(() -> new EtudiantNotFoundException("Aucun étudiant trouvé avec le numéro ETU: " + etu));
            
            List<Note> notes = noteRepository.findByEtudiantIdAndAnneeNom(etudiant.getId(), anneeNom);
            
            if (notes.isEmpty()) {
                throw new NotesNotFoundException("Aucune note trouvée pour l'année: " + anneeNom);
            }
            
            return buildNotesResponse(etudiant, notes, anneeNom);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Erreur lors de l'accès à la base de données", ex);
        }
    }
    
    private NotesResponse buildNotesResponse(Etudiant etudiant, List<Note> notes, String periode) {
        List<NoteDTO> noteDTOs = notes.stream()
                .map(note -> new NoteDTO(
                        note.getMatiere().getCode(),
                        note.getMatiere().getIntitule(),
                        note.getMatiere().getCredit(),
                        note.getValeur(),
                        note.getMatiere().getSemestre().getNom()
                ))
                .collect(Collectors.toList());
        
        // Calcul de la moyenne pondérée
        BigDecimal totalNotesPonderees = notes.stream()
                .map(note -> note.getValeur().multiply(BigDecimal.valueOf(note.getMatiere().getCredit())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        int totalCredits = notes.stream()
                .mapToInt(note -> note.getMatiere().getCredit())
                .sum();
        
        BigDecimal moyenne = totalCredits > 0 
                ? totalNotesPonderees.divide(BigDecimal.valueOf(totalCredits), 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
        
        return new NotesResponse(
                etudiant.getNom() + " " + etudiant.getPrenom(),
                etudiant.getEtu(),
                periode,
                noteDTOs,
                moyenne,
                totalCredits
        );
    }
}
