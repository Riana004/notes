package com.notes.notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "matiere")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matiere {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_semestre", nullable = false)
    private Semestre semestre;
    
    @Column(nullable = false, length = 50)
    private String code;
    
    @Column(nullable = false, length = 150)
    private String intitule;
    
    @Column(nullable = false)
    private Integer credit;
    
    @OneToMany(mappedBy = "matiere")
    private List<Note> notes;
}
