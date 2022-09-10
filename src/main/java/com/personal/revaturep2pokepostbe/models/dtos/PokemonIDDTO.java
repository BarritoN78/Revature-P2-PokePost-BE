package com.personal.revaturep2pokepostbe.models.dtos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.personal.revaturep2pokepostbe.models.Pokemon;

import lombok.Data;

@Data
@Entity
@Table(name = "pokemon")
public class PokemonIDDTO {
	@Id
	private int id;
	
	/*Custom Constructors*/
	
	public PokemonIDDTO(Pokemon convertPoke) {
		this.id = convertPoke.getId();
	}
}
