package com.personal.revaturep2pokepostbe.models;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private List<Ability> abilities;

    @Transient
    private List<Move> moves;
}
