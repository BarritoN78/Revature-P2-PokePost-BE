package com.personal.revaturep2pokepostbe.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Move {
    private String name;
    private String url;
    private String typeOfMove;
    private int levelLearnedAt;
}
