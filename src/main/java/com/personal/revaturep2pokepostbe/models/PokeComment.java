package com.personal.revaturep2pokepostbe.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.personal.revaturep2pokepostbe.models.dtos.PokemonIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PokeComment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToMany
	private PokemonIDDTO pokeID;
	@ManyToMany
	private UserIDDTO author;
	private String content;
	private int likes;
	private int reports;
	private Date postDate;
}
