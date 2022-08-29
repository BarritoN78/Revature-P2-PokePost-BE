package com.personal.revaturep2pokepostbe.models;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonMoves {
    private Set<Move> levelMoves;
    private Set<Move> eggMoves;    
    private Set<Move> tutorMoves;
    private Set<Move> machineMoves;    
    private Set<Move> otherMoves;

}
