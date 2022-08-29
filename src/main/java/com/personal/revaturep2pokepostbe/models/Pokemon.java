package com.personal.revaturep2pokepostbe.models;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pokemon {
	@Id
	@Column
    private int id;

	@Column(name="name")
    private String name;

	@Column(name="gen")
    private int generation;

	@Column(name="sprite")
	private String imageUrl;

	@Transient 
    private int height;

	@Transient
    private int weight;

	@Transient
	private String[] types;

	@Transient
	private Map<String, Integer> baseStats;

	@Transient
    private String category;

	@Transient
    private String description;

	@Transient
    private List<String[]> evolutionChain;

    @Transient
    private List<Map<String, String>> locationVersions;

    @Transient
    private int baseExperience;
    
    @Transient
    private List<Ability> abilities;

    @Transient
    private PokemonMoves moves;
}
