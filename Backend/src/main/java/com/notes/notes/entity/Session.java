package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "session")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session")
    private Long idSession;
    
    @Column(nullable = false, length = 50)
    private String intitule;
    
    @Column(name = "date_", nullable = false)
    private LocalDate date;
    
    @OneToMany(mappedBy = "session")
    private List<Examen> examens;
}

