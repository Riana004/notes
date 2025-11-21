package com.notes.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDetailDTO {
    private String ue;
    private String intitule;
    private Integer credit;
    private BigDecimal note;
    private Boolean isOption;
}
