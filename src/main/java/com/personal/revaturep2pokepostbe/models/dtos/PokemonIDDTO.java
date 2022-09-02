package com.personal.revaturep2pokepostbe.models.dtos;

import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "pokemon")
public class PokemonIDDTO {
	private int id;
}
